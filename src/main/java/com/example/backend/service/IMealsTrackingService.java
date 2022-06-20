package com.example.backend.service;

import com.example.backend.model.MealsTracking;

import java.util.List;

public interface IMealsTrackingService {
    List<MealsTracking> findAll();
//    List<MealsTracking> findByMealFoodId(Long foodId);
    MealsTracking findById(Long id);

    List<MealsTracking> findByUserstrackingId(Long userstrackingId);
    MealsTracking save(MealsTracking mealsTracking);

    Double calculateMealVolumeByMealTrackingId(Long mealTrackingId);
    void delete(Long id);
}
