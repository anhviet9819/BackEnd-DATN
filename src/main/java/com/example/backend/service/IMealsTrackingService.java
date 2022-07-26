package com.example.backend.service;

import com.example.backend.model.MealsTracking;

import java.time.Instant;
import java.util.List;

public interface IMealsTrackingService {
    List<MealsTracking> findAll();
//    List<MealsTracking> findByMealFoodId(Long foodId);
    MealsTracking findById(Long id);

    List<MealsTracking> findByUserstrackingId(Long userstrackingId);
    MealsTracking save(MealsTracking mealsTracking);

    Double calculateMealVolumeByMealTrackingId(Long mealTrackingId);

    void calculateFoodNutritionAndMealNutritionByMealTrackingId(Long mealTrackingId);

    void calculateFoodNutritionAndMealNutritionByMealFoodAdding(Long mealTrackingId, Long foodId);
    void delete(Long id);

    Double findMealCaloriesServingOneDay(Long userstrackingid, Instant createdAtStart, Instant createdAtEnd);
}
