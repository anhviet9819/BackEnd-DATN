package com.example.backend.controller;

import com.example.backend.model.MealFood;
import com.example.backend.service.IMealFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mealfood")
public class MealFoodController {
    @Autowired
    public IMealFoodService mealFoodService;

    @GetMapping("/search")
    public List<MealFood> findAllMealFood(){
        return mealFoodService.findAll();
    }

    @GetMapping("/details/mealstracking/{mealstrackingid}")
    public List<MealFood> findAllByMealsTrackingId(@PathVariable("mealstrackingid")Long mealstrackingid){
        return mealFoodService.findAllByMealsTrackingId(mealstrackingid);
    }

    @GetMapping("/details/food/{foodid}")
    public List<MealFood> findAllByFoodId(@PathVariable("foodid")Long foodid){
        return mealFoodService.findAllByFoodId(foodid);
    }
}
