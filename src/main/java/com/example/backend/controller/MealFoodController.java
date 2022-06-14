package com.example.backend.controller;

import com.example.backend.model.MealFood;
import com.example.backend.model.MealFoodId;
import com.example.backend.repository.FoodRepository;
import com.example.backend.repository.MealFoodRepository;
import com.example.backend.repository.MealsTrackingRepository;
import com.example.backend.service.IMealFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id")MealFoodId mealFoodId) {
//        mealFoodRepository.deleteById(mealFoodId);
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/delete")
    public void deleteByMealFoodId(@RequestParam()Long meal_tracking_id,
                                                @RequestParam()Long food_id) {

    }
}
