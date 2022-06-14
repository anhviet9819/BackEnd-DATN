package com.example.backend.service.impl;

import com.example.backend.model.Food;
import com.example.backend.model.NutritionFact;
import com.example.backend.repository.NutritionFactRepository;
import com.example.backend.service.INutritionFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionFactService implements INutritionFactService {
    @Autowired
    public NutritionFactRepository nutritionFactRepository;

    @Override
    public List<NutritionFact> findAll() {
        return nutritionFactRepository.findAll();
    }

    @Override
    public NutritionFact findByFoodId(Long foodId) {
        return nutritionFactRepository.findNutritionFactByFoodId(foodId);
    }

    @Override
    public NutritionFact save(NutritionFact nutritionFact) {
        return nutritionFactRepository.save(nutritionFact);
    }

    @Override
    public void deleteNutritonFactByFoodId(Long foodId) {
        nutritionFactRepository.deleteNutritionFactByFoodId(foodId);
    }
}
