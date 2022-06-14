package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.*;
import com.example.backend.repository.FoodGroupRepository;
import com.example.backend.repository.FoodRepository;
import com.example.backend.repository.MealsTrackingRepository;
import com.example.backend.service.IFoodGroupService;
import com.example.backend.service.IFoodService;
import com.example.backend.service.INutritionFactService;
import com.example.backend.service.impl.NutritionFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    public IFoodService foodService;

    @Autowired
    public FoodGroupRepository foodGroupRepository;

    @Autowired
    public MealsTrackingRepository mealsTrackingRepository;

    @Autowired
    public INutritionFactService nutritionFactService;

    @Autowired
    public FoodRepository foodRepository;

    @GetMapping("/search")
    public Page<Food> queryByGroupNameOrAll(
//            @RequestParam(name = "group_name", required = false)String FoodGroupName,
                                            @RequestParam(defaultValue = "0")int page,
                                            @RequestParam(defaultValue = "8")int size,
                                            @RequestParam(defaultValue = "") String nameContaining){
//        if(FoodGroupName != null){
//            FoodGroup foodGroup = foodGroupRepository.findByNameContaining(FoodGroupName);  //Tim xem trong FoodGroup co group_name dang tim khong
//            if(foodGroup != null){
//                Long foodGroupId = foodGroup.getId();
//                return foodService.queryByFoodGroup(foodGroupId);
//            }
//            else return foodService.queryAll();
//        }
        Pageable paging = PageRequest.of(page, size);
        return foodRepository.findAllFoodByNameContainingOrderById(paging, nameContaining);  // Chua throw exception
    }
//    @GetMapping("/search")
//    public Page<Food> findAll(@RequestParam(defaultValue = "0")int page,
//    @RequestParam(defaultValue = "10")int size,
//    @RequestParam(defaultValue = "")String nameContaining){
//        Pageable paging = PageRequest.of(page, size);
//        return foodRepository.findFoodByNameContaining(paging, nameContaining);
//    }

    @GetMapping("/details/{id}")
    public Food findById(@PathVariable("id")Long id){
        return foodService.findById(id);
    }

    @GetMapping("/details/count/{foodgroupid}")
    public Long countByFoodGroupId(@PathVariable("foodgroupid")Long foodGroupId){
        return foodRepository.countFoodByFoodGroupId(foodGroupId);
    }

//    @GetMapping("details/mealstracking/{mealstrackingid}/food")
//    public List<Food> getAllFoodByMealsTrackingId(@PathVariable("mealstrackingid")Long mealstrackingid){
//        if (!mealsTrackingRepository.existsById(mealstrackingid)) {
//            throw new ResourceNotFoundException("Not found Food with id = " + mealstrackingid);
//        }
//        return foodService.queryByMealsTrackingId(mealstrackingid);
//    }

    @PostMapping("/create/foodgroup/{foodgroupid}")
    public Food createFoodByFoodGroupId(@PathVariable("foodgroupid")Long foodGroupId,
                                        @RequestBody Food food) {
        FoodGroup foodGroup = foodGroupRepository.findById(foodGroupId).orElse(null);
        Food food1 = foodService.findById(food.getId());
        if(food1 != null) throw new DuplicateIdException("Food", food.getId());

//        Long foodGroupThatFoodAdded = foodGroupId;
        Long foodId = foodGroupId * 100000000 + foodRepository.countFoodByFoodGroupId(foodGroupId) + 1;

//        Food foodSave = new Food(foodId, food.getName(), Instant.now(), foodGroup);

        Food foodSave = foodService.save(new Food(foodId, food.getName(), Instant.now(), foodGroup));

        nutritionFactService.save(new NutritionFact(foodSave));

        return foodSave;
    }

//    @PostMapping("/add/mealstracking/{mealstrackingid}/food")
//    public Food addFood(@PathVariable("mealstrackingid")Long mealstrackingid,
//                        @RequestBody Food foodRequest){
//        MealsTracking mealsTracking = mealsTrackingRepository.findById(mealstrackingid).orElse(null);

//        Food food = foodService.findByExistName(foodRequest.getName());

        // food is existed
//        if (food.getId() != 0L) {
//            Food food1 = foodService.findById(foodId);

//            mealsTracking.addFood(food);
//            mealsTrackingRepository.save(mealsTracking);
//            mealsTracking.showFood();
//            return food;
//        }

        // add and create new food
//        foodService.save(food);
//        mealsTracking.addFood(foodRequest);
//        return foodService.save(foodRequest);
//    }

        @PostMapping("/add/mealstracking/{mealstrackingid}/food")
        public Food addFood(@PathVariable("mealstrackingid")Long mealstrackingid,
                        @RequestBody FoodVolume foodVolume){
        MealsTracking mealsTracking = mealsTrackingRepository.findById(mealstrackingid).orElse(null);
        Food food = foodService.findByExistName(foodVolume.getFood().getName());

//        FoodGroup foodGroup1 = foodGroupRepository.findByNameContaining(foodVolume.getFood().getFoodGroup().getGroup_name());
//        System.out.println(foodGroup1.getId()* 100000000 + foodRepository.countFoodByFoodGroupId(foodGroup1.getId()) + 1);
//        Food foodAdded = foodService.save(new Food(foodGroup1.getId()* 100000000 + foodRepository.countFoodByFoodGroupId(foodGroup1.getId()) + 1, foodVolume.getFood().getName(), Instant.now(), foodGroupRepository.findByNameContaining(foodVolume.getFood().getFoodGroup().getGroup_name())));
//        System.out.println(foodAdded.getName());

        // food is existed
        if (food != null) {
            NutritionFact nutritionFactFoodAdded = nutritionFactService.findByFoodId(food.getId());
            Double foodAddedCalories = 0.0;
            foodAddedCalories = foodVolume.getFood_volume()/nutritionFactFoodAdded.getServing_weight_grams()*nutritionFactFoodAdded.getCalories();
//            System.out.println("Kiem tra ti: " + foodAddedCalories);
            MealFoodId mealFoodId = new MealFoodId(food.getId(), mealstrackingid);
            mealsTracking.addFood(new MealFood(mealFoodId, foodVolume.getFood_volume(), mealsTracking, food));

            if(mealsTracking.getMeal_volume() == null){
                mealsTracking.setMeal_volume(0.0);
            }
            mealsTracking.setMeal_volume(mealsTracking.getMeal_volume() + foodAddedCalories);

            mealsTrackingRepository.save(mealsTracking);
            return food;
        }

        // add and create new food
        FoodGroup foodGroup = foodGroupRepository.findByNameContaining(foodVolume.getFood().getFoodGroup().getGroup_name());
        Long foodGroupIdThatFoodAdded = foodGroup.getId();
        Long foodAddedId = foodGroupIdThatFoodAdded * 100000000 + foodRepository.countFoodByFoodGroupId(foodGroupIdThatFoodAdded) + 1;

        foodVolume.getFood().setId(foodAddedId);
        foodVolume.getFood().setFoodGroup(foodGroup);

        Food foodAdded1 = foodService.save(new Food(foodAddedId, foodVolume.getFood().getName(), Instant.now(), foodGroup));
        MealFoodId mealFoodId = new MealFoodId(foodAdded1.getId(), mealstrackingid);
        mealsTracking.addFood(new MealFood(mealFoodId, foodVolume.getFood_volume(), mealsTracking, foodAdded1));
        mealsTrackingRepository.save(mealsTracking);

        return foodAdded1;
    }

    @PutMapping("/update/{id}")
    public Food updateFoodById(@PathVariable("id") Long id,
                                     @Validated @RequestBody Food foodRequest) {
        Food food = foodService.findById(id);

        food.setName(foodRequest.getName());
        if(foodRequest.getImage() != null){
            food.setImage(foodRequest.getImage());
        }
        if(foodRequest.getLanguage() != null){
            food.setLanguage(foodRequest.getLanguage());
        }
        if(foodRequest.getOwner() != null){
            food.setOwner(foodRequest.getOwner());
        }
        if(foodRequest.getScope() != null){
            food.setScope(foodRequest.getScope());
        }
        if(foodRequest.getIs_ingredient() != null){
            food.setIs_ingredient(foodRequest.getIs_ingredient());
        }
        if(foodRequest.getFoodGroup() != null){
            food.setFoodGroup(foodRequest.getFoodGroup());
        }


        return foodService.save(food);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//        nutritionFactService.deleteNutritonFactByFoodId(id);
        Food foodDelete = foodService.findById(id);
        foodRepository.delete(foodDelete);
        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/delete/mealstracking/{mealstrackingid}/food/{foodid}")
//    public ResponseEntity<HttpStatus> deleteFoodFromMealsTracking(@PathVariable("mealstrackingid") Long mealstrackingId,
//                                                                  @PathVariable("foodid") Long foodId) {
//        MealsTracking mealsTracking = mealsTrackingRepository.findById(mealstrackingId)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found MealsTracking with id = " + mealstrackingId));
//
//        mealsTracking.removeFood(foodId);
//        mealsTrackingRepository.save(mealsTracking);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
