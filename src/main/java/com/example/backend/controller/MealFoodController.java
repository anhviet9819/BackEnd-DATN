package com.example.backend.controller;

import com.example.backend.model.MealFood;
import com.example.backend.model.MealFoodId;
import com.example.backend.model.MealsTracking;
import com.example.backend.repository.FoodRepository;
import com.example.backend.repository.MealFoodRepository;
import com.example.backend.repository.MealsTrackingRepository;
import com.example.backend.service.IMealFoodService;
import com.example.backend.service.impl.MealsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mealfood")

public class MealFoodController {
    @Autowired
    public IMealFoodService mealFoodService;

    @Autowired
    public MealFoodRepository mealFoodRepository;

    @Autowired
    public MealsTrackingRepository mealsTrackingRepository;

    @Autowired
    public FoodRepository foodRepository;

    @Autowired
    public MealsTrackingService mealsTrackingService;

    @GetMapping("/search")
    public List<MealFood> findAllMealFood(){
        return mealFoodService.findAll();
    }

    @GetMapping("/details/mealstracking/{mealstrackingid}/mealfood")
    public List<MealFood> findAllByMealsTrackingId(@PathVariable("mealstrackingid")Long mealstrackingid){
        return mealFoodService.findAllByMealsTrackingId(mealstrackingid);
    }

    @GetMapping("/details/food/{foodid}")
    public List<MealFood> findAllByFoodId(@PathVariable("foodid")Long foodid){
        return mealFoodService.findAllByFoodId(foodid);
    }

    @PutMapping("/delete")
    public MealFood updateByMealFoodId(@RequestParam()Long mealTrackingId,
                                   @RequestParam()Long foodId,
                                   @Validated @RequestBody MealFood mealFoodRequest) {
        MealsTracking mealsTracking = mealsTrackingRepository.findById(mealTrackingId).orElse(null);

        MealFood mealFoodUpdate = mealFoodRepository.findByMealsTrackingIdAndFoodId(mealTrackingId, foodId);
        if(mealFoodRequest.getFood_volume() != null){
            mealFoodUpdate.setFood_volume(mealFoodRequest.getFood_volume());
        }
        MealFood mealFood = mealFoodRepository.save(mealFoodUpdate);

        Double mealCaloriesServing = mealsTrackingService.calculateMealVolumeByMealTrackingId(mealTrackingId);
        mealsTracking.setMeal_volume(mealCaloriesServing);
        mealsTrackingService.save(mealsTracking);

        return mealFood;
    }

    @DeleteMapping("/delete")
    public void deleteByMealFoodId(@RequestParam()Long mealTrackingId,
                                   @RequestParam()Long foodId) {
        MealsTracking mealsTracking = mealsTrackingRepository.findById(mealTrackingId).orElse(null);

        MealFood mealFoodDelete = mealFoodRepository.findByMealsTrackingIdAndFoodId(mealTrackingId, foodId);
        mealFoodRepository.delete(mealFoodDelete);

//        Double mealCaloriesServing = mealsTrackingService.calculateMealVolumeByMealTrackingId(mealTrackingId);
//        mealsTracking.setMeal_volume(mealCaloriesServing);
//        mealsTrackingService.save(mealsTracking);
//        return ResponseEntity.ok().build();

        mealsTrackingService.calculateFoodNutritionAndMealNutritionByMealTrackingId(mealTrackingId);
    }
}
