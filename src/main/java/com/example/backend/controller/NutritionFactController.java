package com.example.backend.controller;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Food;
import com.example.backend.model.MealFood;
import com.example.backend.model.MealsTracking;
import com.example.backend.model.NutritionFact;
import com.example.backend.repository.MealFoodRepository;
import com.example.backend.service.IFoodService;
import com.example.backend.service.INutritionFactService;
import com.example.backend.service.impl.MealsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritionfact")
public class NutritionFactController {
    @Autowired
    public INutritionFactService nutritionFactService;

    @Autowired
    public IFoodService foodService;

    @Autowired
    public MealFoodRepository mealFoodRepository;

    @Autowired
    public MealsTrackingService mealsTrackingService;

    @GetMapping("/search")
    public List<NutritionFact> getAll(){
        return nutritionFactService.findAll();
    }

    @GetMapping("/details/food/{foodid}")
    public NutritionFact findNutritionFactByFoodId(@PathVariable("foodid")Long foodId){
        return nutritionFactService.findByFoodId(foodId);
    }

    @PostMapping("/create/food/{foodid}")
    public NutritionFact createNutritionFactByFoodId(@PathVariable("foodid")Long foodId,
                                                     @Validated @RequestBody NutritionFact nutritionFact){
        Food food = foodService.findById(foodId);

        return nutritionFactService.save(nutritionFact);
    }

    @PutMapping("/update/food/{foodid}")
    public NutritionFact updateByFoodId(@PathVariable("foodid")Long foodid,
                                        @Validated @RequestBody NutritionFact nutritionFactRequest){
        NutritionFact nutritionFact = nutritionFactService.findByFoodId(foodid);
        if(nutritionFact == null) throw new ResourceNotFoundException("Not found nutritionFact with FoodId: "+ foodid);
//        if(nutritionFactRequest.getServing_unit() != null){
//            nutritionFact.setServing_unit(nutritionFactRequest.getServing_unit());
//        }
//        if(nutritionFactRequest.getServing_weight_grams() != null){
//            nutritionFact.setServing_weight_grams(nutritionFactRequest.getServing_weight_grams());
//        }
        nutritionFact.setServing_unit("g");
        nutritionFact.setServing_weight_grams(100.0);
        if(nutritionFactRequest.getCalories() != null){
            nutritionFact.setCalories(nutritionFactRequest.getCalories());
        }
        if(nutritionFactRequest.getFat() != null){
            nutritionFact.setFat(nutritionFactRequest.getFat());
        }
        if(nutritionFactRequest.getSaturated_fat() != null){
            nutritionFact.setSaturated_fat(nutritionFactRequest.getSaturated_fat());
        }
        if(nutritionFactRequest.getTrans_fat() != null){
            nutritionFact.setTrans_fat(nutritionFactRequest.getTrans_fat());
        }
        if(nutritionFactRequest.getProtein() != null){
            nutritionFact.setProtein(nutritionFactRequest.getProtein());
        }
        if(nutritionFactRequest.getCholesterol() != null){
            nutritionFact.setCholesterol(nutritionFactRequest.getCholesterol());
        }
        if(nutritionFactRequest.getSodium() != null){
            nutritionFact.setSodium(nutritionFactRequest.getSodium());
        }
        if(nutritionFactRequest.getPotassium() != null){
            nutritionFact.setPotassium(nutritionFactRequest.getPotassium());
        }
        if(nutritionFactRequest.getCarbohydrates() != null){
            nutritionFact.setCarbohydrates(nutritionFactRequest.getCarbohydrates());
        }
        if(nutritionFactRequest.getDiatery_fiber() != null){
            nutritionFact.setDiatery_fiber(nutritionFactRequest.getDiatery_fiber());
        }
        if(nutritionFactRequest.getSugars() != null){
            nutritionFact.setSugars(nutritionFactRequest.getSugars());
        }
        if(nutritionFactRequest.getCalcium() != null){
            nutritionFact.setCalcium(nutritionFactRequest.getCalcium());
        }
        if(nutritionFactRequest.getVitamin_a() != null){
            nutritionFact.setVitamin_a(nutritionFactRequest.getVitamin_a());
        }
        if(nutritionFactRequest.getVitamin_c() != null){
            nutritionFact.setVitamin_c(nutritionFactRequest.getVitamin_c());
        }
        if(nutritionFactRequest.getIron() != null){
            nutritionFact.setIron(nutritionFactRequest.getIron());
        }
        if(nutritionFactRequest.getUpdated_at() != null){
            nutritionFact.setUpdated_at(nutritionFactRequest.getUpdated_at());
        }
        NutritionFact nutritionFact1 = nutritionFactService.save(nutritionFact);

        // update lai mealNutrition khi update nutritionFact
        List<MealFood> mealFoods = mealFoodRepository.findMealFoodByFoodId(foodid);
        if(mealFoods != null){
//            for(MealFood mealFoodEl: mealFoods){
//                Double mealCaloriesUpdate = 0.0;
//                mealCaloriesUpdate = mealsTrackingService.calculateMealVolumeByMealTrackingId(mealFoodEl.getId().getMeal_tracking_id());
//                MealsTracking mealsTrackingUpdate = mealsTrackingService.findById(mealFoodEl.getId().getMeal_tracking_id());
//                mealsTrackingUpdate.setMeal_volume(mealCaloriesUpdate);
//                mealsTrackingService.save(mealsTrackingUpdate);
//            }
            for(MealFood mealFoodEl: mealFoods){
             mealsTrackingService.calculateFoodNutritionAndMealNutritionByMealFoodAdding(mealFoodEl.getMealsTracking().getId(), mealFoodEl.getFood().getId());
            }
        }

        return nutritionFact1;
    }

    @DeleteMapping("/delete/food/{foodid}")
    public ResponseEntity<?> deleteNutritionFactByFoodid(@PathVariable("foodid")Long foodid){
        nutritionFactService.deleteNutritonFactByFoodId(foodid);
        return ResponseEntity.ok().build();
    }
}
