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

import java.time.Instant;
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
    public void calculateFoodNutritionAndMealNutritionByMealTrackingId(Long mealTrackingId) {
        MealsTracking mealsTracking = mealsTrackingRepository.findById(mealTrackingId).orElse(null);
        List<MealFood> mealFoods = mealFoodRepository.findMealFoodByMealsTrackingId(mealTrackingId);
        Double mealVolume = 0.0;
        Double mealCalories = 0.0;
        Double mealFat = 0.0;
        Double mealSaturatedFat = 0.0;
        Double mealTransFat = 0.0;
        Double mealProtein = 0.0;
        Double mealCholesterol = 0.0;
        Double mealSodium = 0.0;
        Double mealPotassium = 0.0;
        Double mealCarbohydrates = 0.0;
        Double mealDiateryFiber = 0.0;
        Double mealSugars = 0.0;
        Double mealVitaminA = 0.0;
        Double mealVitaminC = 0.0;
        Double mealCalcium = 0.0;
        Double mealIron = 0.0;
        for(int i = 0; i < mealFoods.size(); i++){
            MealFood mealFood = mealFoodRepository.findByMealsTrackingIdAndFoodId(mealFoods.get(i).getId().getMeal_tracking_id(), mealFoods.get(i).getId().getFood_id());
            Double foodVolume = mealFoods.get(i).getFood_volume();
            NutritionFact nutritionFactSearching = nutritionFactRepository.findNutritionFactByFoodId(mealFoods.get(i).getFood().getId());
            Double servingWeightGrams = nutritionFactSearching.getServing_weight_grams();

            mealVolume += foodVolume;

            Double calories = foodVolume / servingWeightGrams * nutritionFactSearching.getCalories();
            mealFood.setCalories(calories);
            mealCalories += calories;
            if(nutritionFactSearching.getFat() == null){
                mealFood.setFat(0.0);
            }else{
                Double fat = foodVolume / servingWeightGrams * nutritionFactSearching.getFat();
                mealFood.setFat(Math.round(fat*100.0)/100.0);
                mealFat += mealFood.getFat();
            }
            if(nutritionFactSearching.getSaturated_fat() == null){
                mealFood.setSaturated_fat(0.0);
            }else {
                Double saturated_fat = foodVolume / servingWeightGrams * nutritionFactSearching.getSaturated_fat();
                mealFood.setSaturated_fat(Math.round(saturated_fat*100.0)/100.0);
                mealSaturatedFat += mealFood.getSaturated_fat();
            }
            if(nutritionFactSearching.getProtein() == null){
                mealFood.setProtein(0.0);
            }else{
                Double protein = foodVolume / servingWeightGrams * nutritionFactSearching.getProtein();
                mealFood.setProtein(Math.round(protein*100.0)/100.0);
                mealProtein += mealFood.getProtein();
            }

            if(nutritionFactSearching.getCholesterol() == null){
                mealFood.setCholesterol(0.0);
            }else{
                Double cholesterol = foodVolume / servingWeightGrams * nutritionFactSearching.getCholesterol();
                mealFood.setCholesterol(Math.round(cholesterol*100.0)/100.0);
                mealCholesterol += mealFood.getCholesterol();
            }
            if(nutritionFactSearching.getSodium() == null){
                mealFood.setSodium(0.0);
            }else{
                Double sodium = foodVolume / servingWeightGrams * nutritionFactSearching.getSodium();
                mealFood.setSodium(Math.round(sodium*100.0)/100.0);
                mealSodium += mealFood.getSodium();
            }

            if(nutritionFactSearching.getPotassium() == null){
                mealFood.setPotassium(0.0);
            }else{
                Double potassium = foodVolume / servingWeightGrams * nutritionFactSearching.getPotassium();
                mealFood.setPotassium(Math.round(potassium*100.0)/100.0);
                mealPotassium += mealFood.getPotassium();
            }

            if(nutritionFactSearching.getCarbohydrates() == null){
                mealFood.setCarbohydrates(0.0);
            }else{
                Double carbohydrates = foodVolume / servingWeightGrams * nutritionFactSearching.getCarbohydrates();
                mealFood.setCarbohydrates(Math.round(carbohydrates*100.0)/100.0);
                mealCarbohydrates += mealFood.getCarbohydrates();
            }

            if(nutritionFactSearching.getDiatery_fiber() == null){
                mealFood.setDiatery_fiber(0.0);
            }else{
                Double diatery_fiber = foodVolume / servingWeightGrams * nutritionFactSearching.getDiatery_fiber();
                mealFood.setDiatery_fiber(Math.round(diatery_fiber*100.0)/100.0);
                mealDiateryFiber += mealFood.getDiatery_fiber();
            }
            if(nutritionFactSearching.getSugars() == null){
                mealFood.setSugars(0.0);
            }else{
                Double sugars = foodVolume / servingWeightGrams * nutritionFactSearching.getSugars();
                mealFood.setSugars(Math.round(sugars*100.0)/100.0);
                mealSugars += mealFood.getSugars();
            }
            if(nutritionFactSearching.getVitamin_c() == null){
                mealFood.setVitamin_c(0.0);
            }else{
                Double vitamin_c = foodVolume / servingWeightGrams * nutritionFactSearching.getVitamin_c();
                mealFood.setVitamin_c(Math.round(vitamin_c*100.0)/100.0);
                mealVitaminC += mealFood.getVitamin_c();
            }

            if(nutritionFactSearching.getCalcium() == null){
                mealFood.setCalcium(0.0);
            }else{
                Double calcium = foodVolume / servingWeightGrams * nutritionFactSearching.getCalcium();
                mealFood.setCalcium(Math.round(calcium*100.0)/100.0);
                mealCalcium += mealFood.getCalcium();
            }

            if(nutritionFactSearching.getIron() == null){
                mealFood.setIron(0.0);
            }else{
                Double iron = foodVolume / servingWeightGrams * nutritionFactSearching.getIron();
                mealFood.setIron(Math.round(iron*100.0)/100.0);
                mealIron += mealFood.getIron();
            }
            if(nutritionFactSearching.getVitamin_a() == null){
                mealFood.setVitamin_a(0.0);
            }else{
                Double vitamin_a = foodVolume / servingWeightGrams * nutritionFactSearching.getVitamin_c();
                mealFood.setVitamin_a(Math.round(vitamin_a*100.0)/100.0);
                mealVitaminA += mealFood.getVitamin_a();
            }
            if(nutritionFactSearching.getTrans_fat() == null){
                mealFood.setTrans_fat(0.0);
            }else{
                Double trans_fat = foodVolume / servingWeightGrams * nutritionFactSearching.getTrans_fat();
                mealFood.setTrans_fat(Math.round(trans_fat*100.0)/100.0);
                mealTransFat += mealFood.getTrans_fat();
            }
            mealFoodRepository.save(mealFood);
        }
        mealsTracking.setMeal_volume(mealVolume);
        mealsTracking.setMeal_calories(mealCalories);
        mealsTracking.setMeal_fat(mealFat);
        mealsTracking.setMeal_trans_fat(mealTransFat);
        mealsTracking.setMeal_saturated_fat(mealSaturatedFat);
        mealsTracking.setMeal_protein(mealProtein);
        mealsTracking.setMeal_cholesterol(mealCholesterol);
        mealsTracking.setMeal_sodium(mealSodium);
        mealsTracking.setMeal_potassium(mealPotassium);
        mealsTracking.setMeal_carbohydrates(mealCarbohydrates);
        mealsTracking.setMeal_diatery_fiber(mealDiateryFiber);
        mealsTracking.setMeal_sugars(mealSugars);
        mealsTracking.setMeal_vitamin_a(mealVitaminA);
        mealsTracking.setMeal_vitamin_c(mealVitaminC);
        mealsTracking.setMeal_calcium(mealCalcium);
        mealsTracking.setMeal_iron(mealIron);
        mealsTrackingRepository.save(mealsTracking);
    }

    @Override
    public void calculateFoodNutritionAndMealNutritionByMealFoodAdding(Long mealTrackingId, Long mealFoodId) {
        MealsTracking mealsTracking = mealsTrackingRepository.findById(mealTrackingId).orElse(null);
        MealFood mealFood = mealFoodRepository.findByMealsTrackingIdAndFoodId(mealTrackingId, mealFoodId);

        Double foodVolume = mealFood.getFood_volume();
        NutritionFact nutritionFactSearching = nutritionFactRepository.findNutritionFactByFoodId(mealFood.getFood().getId());
        Double servingWeightGrams = nutritionFactSearching.getServing_weight_grams();

        if(nutritionFactSearching.getCalories() == null){
            nutritionFactSearching.setCalories(0.0);
        }
        if(nutritionFactSearching.getIron() == null){
            nutritionFactSearching.setIron(0.0);
        }
        if(nutritionFactSearching.getCalcium() == null){
            nutritionFactSearching.setCalcium(0.0);
        }
        if(nutritionFactSearching.getVitamin_a() == null){
            nutritionFactSearching.setVitamin_a(0.0);
        }
        if(nutritionFactSearching.getVitamin_c() == null){
            nutritionFactSearching.setVitamin_c(0.0);
        }
        if(nutritionFactSearching.getCarbohydrates() == null){
            nutritionFactSearching.setCarbohydrates(0.0);
        }
        if(nutritionFactSearching.getSugars() == null){
            nutritionFactSearching.setSugars(0.0);
        }
        if(nutritionFactSearching.getSugars() == null){
            nutritionFactSearching.setSugars(0.0);
        }
        if(nutritionFactSearching.getSodium() == null){
            nutritionFactSearching.setSodium(0.0);
        }
        if(nutritionFactSearching.getPotassium() == null){
            nutritionFactSearching.setPotassium(0.0);
        }
        if(nutritionFactSearching.getDiatery_fiber() == null){
            nutritionFactSearching.setDiatery_fiber(0.0);
        }
        if(nutritionFactSearching.getCholesterol() == null){
            nutritionFactSearching.setCholesterol(0.0);
        }
        if(nutritionFactSearching.getProtein() == null){
            nutritionFactSearching.setProtein(0.0);
        }
        if(nutritionFactSearching.getFat() == null){
            nutritionFactSearching.setFat(0.0);
        }
        if(nutritionFactSearching.getTrans_fat() == null){
            nutritionFactSearching.setTrans_fat(0.0);
        }
        if(nutritionFactSearching.getSaturated_fat() == null){
            nutritionFactSearching.setSaturated_fat(0.0);
        }

        mealsTracking.setMeal_volume(mealsTracking.getMeal_volume() + foodVolume);

        Double calories = foodVolume / servingWeightGrams * nutritionFactSearching.getCalories();
        mealFood.setCalories(calories);
        mealsTracking.setMeal_calories(mealsTracking.getMeal_calories() + calories);

        if(nutritionFactSearching.getFat() == null){
            mealFood.setFat(0.0);
        }else{
            Double fat = foodVolume / servingWeightGrams * nutritionFactSearching.getFat();
            mealFood.setFat(Math.round(fat*100.0)/100.0);
            mealsTracking.setMeal_fat(mealsTracking.getMeal_fat() + fat);
        }
        if(nutritionFactSearching.getSaturated_fat() == null){
            mealFood.setSaturated_fat(0.0);
        }else {
            Double saturated_fat = foodVolume / servingWeightGrams * nutritionFactSearching.getSaturated_fat();
            mealFood.setSaturated_fat(Math.round(saturated_fat*100.0)/100.0);
            mealsTracking.setMeal_saturated_fat(mealsTracking.getMeal_saturated_fat() + saturated_fat);
        }
        if(nutritionFactSearching.getProtein() == null){
            mealFood.setProtein(0.0);
        }else{
            Double protein = foodVolume / servingWeightGrams * nutritionFactSearching.getProtein();
            mealFood.setProtein(Math.round(protein*100.0)/100.0);
            mealsTracking.setMeal_protein(mealsTracking.getMeal_protein() + protein);
        }

        if(nutritionFactSearching.getCholesterol() == null){
            mealFood.setCholesterol(0.0);
        }else{
            Double cholesterol = foodVolume / servingWeightGrams * nutritionFactSearching.getCholesterol();
            mealFood.setCholesterol(Math.round(cholesterol*100.0)/100.0);
            mealsTracking.setMeal_cholesterol(mealsTracking.getMeal_cholesterol() + cholesterol);
        }
        if(nutritionFactSearching.getSodium() == null){
            mealFood.setSodium(0.0);
        }else{
            Double sodium = foodVolume / servingWeightGrams * nutritionFactSearching.getSodium();
            mealFood.setSodium(Math.round(sodium*100.0)/100.0);
            mealsTracking.setMeal_sodium(mealsTracking.getMeal_sodium() + sodium);
        }

        if(nutritionFactSearching.getPotassium() == null){
            mealFood.setPotassium(0.0);
        }else{
            Double potassium = foodVolume / servingWeightGrams * nutritionFactSearching.getPotassium();
            mealFood.setPotassium(Math.round(potassium*100.0)/100.0);
            mealsTracking.setMeal_potassium(mealsTracking.getMeal_potassium() + potassium);
        }

        if(nutritionFactSearching.getCarbohydrates() == null){
            mealFood.setCarbohydrates(0.0);
        }else{
            Double carbohydrates = foodVolume / servingWeightGrams * nutritionFactSearching.getCarbohydrates();
            mealFood.setCarbohydrates(Math.round(carbohydrates*100.0)/100.0);
            mealsTracking.setMeal_carbohydrates(mealsTracking.getMeal_carbohydrates() + carbohydrates);
        }

        if(nutritionFactSearching.getDiatery_fiber() == null){
            mealFood.setDiatery_fiber(0.0);
        }else{
            Double diatery_fiber = foodVolume / servingWeightGrams * nutritionFactSearching.getDiatery_fiber();
            mealFood.setDiatery_fiber(Math.round(diatery_fiber*100.0)/100.0);
            mealsTracking.setMeal_diatery_fiber(mealsTracking.getMeal_diatery_fiber() + diatery_fiber);
        }
        if(nutritionFactSearching.getSugars() == null){
            mealFood.setSugars(0.0);
        }else{
            Double sugars = foodVolume / servingWeightGrams * nutritionFactSearching.getSugars();
            mealFood.setSugars(Math.round(sugars*100.0)/100.0);
            mealsTracking.setMeal_sugars(mealsTracking.getMeal_sugars() + sugars);
        }
        if(nutritionFactSearching.getVitamin_c() == null){
            mealFood.setVitamin_c(0.0);
        }else{
            Double vitamin_c = foodVolume / servingWeightGrams * nutritionFactSearching.getVitamin_c();
            mealFood.setVitamin_c(Math.round(vitamin_c*100.0)/100.0);
            mealsTracking.setMeal_vitamin_c(mealsTracking.getMeal_vitamin_c() + vitamin_c);
        }

        if(nutritionFactSearching.getCalcium() == null){
            mealFood.setCalcium(0.0);
        }else{
            Double calcium = foodVolume / servingWeightGrams * nutritionFactSearching.getCalcium();
            mealFood.setCalcium(Math.round(calcium*100.0)/100.0);
            mealsTracking.setMeal_calcium(mealsTracking.getMeal_calcium() + calcium);
        }

        if(nutritionFactSearching.getIron() == null){
            mealFood.setIron(0.0);
        }else{
            Double iron = foodVolume / servingWeightGrams * nutritionFactSearching.getIron();
            mealFood.setIron(Math.round(iron*100.0)/100.0);
            mealsTracking.setMeal_iron(mealsTracking.getMeal_iron() + iron);
        }
        if(nutritionFactSearching.getVitamin_a() == null){
            mealFood.setVitamin_a(0.0);
        }else{
            Double vitamin_a = foodVolume / servingWeightGrams * nutritionFactSearching.getVitamin_c();
            mealFood.setVitamin_a(Math.round(vitamin_a*100.0)/100.0);
            mealsTracking.setMeal_vitamin_a(mealsTracking.getMeal_vitamin_a() + vitamin_a);
        }
        if(nutritionFactSearching.getTrans_fat() == null){
            mealFood.setTrans_fat(0.0);
        }else{
            Double trans_fat = foodVolume / servingWeightGrams * nutritionFactSearching.getTrans_fat();
            mealFood.setTrans_fat(Math.round(trans_fat*100.0)/100.0);
            mealsTracking.setMeal_trans_fat(mealsTracking.getMeal_trans_fat() + trans_fat);
        }
        mealFoodRepository.save(mealFood);
        mealsTrackingRepository.save(mealsTracking);
    }

    @Override
    public void delete(Long id) {
        mealsTrackingRepository.deleteById(id);
    }

    @Override
    public Double findMealCaloriesServingOneDay(Long userstrackingid, Instant createdAtStart, Instant createdAtEnd){
        Double mealCaloriesServingOneday = 0.0;
        List<MealsTracking> mealsTrackingList = mealsTrackingRepository.findAllByUsersTrackingIdAndCreatedAtBetween(userstrackingid, createdAtStart, createdAtEnd);
        for(int i = 0; i < mealsTrackingList.size(); i++){
            mealCaloriesServingOneday += mealsTrackingList.get(i).getMeal_volume();
        }
        return mealCaloriesServingOneday;
    }

}
