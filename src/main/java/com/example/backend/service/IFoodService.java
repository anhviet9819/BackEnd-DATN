package com.example.backend.service;

import com.example.backend.model.Food;

import java.util.List;

public interface IFoodService {
    List<Food> queryAll();
    List<Food> queryByFoodGroup(Long FoodGroupId);
//    List<Food> queryByMealsTrackingId(Long mealsTrackingId);
    Food findById(Long id);
    Food findByExistName(String foodName);
    Food save(Food food);
    void delete(Long id);
    Long generateFoodId(Long foodGroupId);
}
