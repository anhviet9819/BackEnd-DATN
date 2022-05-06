package com.example.backend.service;

import com.example.backend.model.MealFood;

import java.util.List;

public interface IMealFoodService {
    List<MealFood> findAll();
    List<MealFood> findAllByMealsTrackingId(Long mealstrackingId);
    List<MealFood> findAllByFoodId(Long foodId);

}
