package com.example.backend.service.impl;

import com.example.backend.model.MealFood;
import com.example.backend.repository.MealFoodRepository;
import com.example.backend.service.IMealFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealFoodService implements IMealFoodService {
    @Autowired
    public MealFoodRepository mealFoodRepository;

    @Override
    public List<MealFood> findAll() {
        return mealFoodRepository.findAll();
    }

    @Override
    public List<MealFood> findAllByMealsTrackingId(Long mealstrackingId) {
        return mealFoodRepository.findMealFoodByMealsTrackingId(mealstrackingId);
    }

    @Override
    public List<MealFood> findAllByFoodId(Long foodId) {
        return mealFoodRepository.findMealFoodByFoodId(foodId);
    }
}
