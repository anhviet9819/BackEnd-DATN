package com.example.backend.service.impl;

import com.example.backend.model.MealFood;
import com.example.backend.model.MealsTracking;
import com.example.backend.model.NutritionFact;
import com.example.backend.repository.MealFoodRepository;
import com.example.backend.repository.MealsTrackingRepository;
import com.example.backend.repository.NutritionFactRepository;
import com.example.backend.service.IMealsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealsTrackingService implements IMealsTrackingService {

    @Autowired
    public MealsTrackingRepository mealsTrackingRepository;

    @Autowired
    public MealFoodRepository mealFoodRepository;

    @Autowired
    public NutritionFactRepository nutritionFactRepository;

    @Override
    public List<MealsTracking> findAll() {
        return mealsTrackingRepository.findAll();
    }

//    @Override
//    public List<MealsTracking> findByMealFoodId(Long foodId) {
//        return mealsTrackingRepository.findMealsTrackingByFoodsId(foodId);
//        return mealsTrackingRepository
//    }

    @Override
    public MealsTracking findById(Long id) {
        return mealsTrackingRepository.findById(id).orElse(null);
    }

    @Override
    public List<MealsTracking> findByUserstrackingId(Long userstrackingId) {
        return mealsTrackingRepository.findMealsTrackingByUsersTrackingId(userstrackingId);
    }

    @Override
    public MealsTracking save(MealsTracking mealsTracking) {
        return mealsTrackingRepository.save(mealsTracking);
    }

    //Tinh toan calories cho bua an
    @Override
    public Double calculateMealVolumeByMealTrackingId(Long mealTrackingId) {
        Double mealVolume = 0.0;
        List<MealFood> mealFoods = mealFoodRepository.findMealFoodByMealsTrackingId(mealTrackingId);
//        System.out.println(mealFoods.size());
        for(int i = 0; i < mealFoods.size(); i++){
//            System.out.println(i);
            Double foodVolume = mealFoods.get(i).getFood_volume();
            NutritionFact nutritionFactSearching = nutritionFactRepository.findNutritionFactByFoodId(mealFoods.get(i).getFood().getId());
            Double servingWeightGrams = nutritionFactSearching.getServing_weight_grams();
            Double foodCaloriesPer100gam = nutritionFactSearching.getCalories();
            Double foodCalories = foodVolume / servingWeightGrams * foodCaloriesPer100gam;
            mealVolume += foodCalories;
        }
        return mealVolume;
    }

    @Override
    public void delete(Long id) {
        mealsTrackingRepository.deleteById(id);
    }
}
