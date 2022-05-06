package com.example.backend.service;

import com.example.backend.model.NutritionFact;

import java.util.List;

public interface INutritionFactService {
    List<NutritionFact> findAll();

    NutritionFact findByFoodId(Long foodId);

    NutritionFact createNutritionFact(NutritionFact nutritionFact);

    void deleteNutritonFactByFoodId(Long foodId);
}
