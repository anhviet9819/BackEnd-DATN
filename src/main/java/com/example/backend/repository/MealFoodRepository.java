package com.example.backend.repository;

import com.example.backend.model.MealFood;
import com.example.backend.model.MealFoodId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface MealFoodRepository extends JpaRepository<MealFood, MealFoodId> {
    List<MealFood> findMealFoodByMealsTrackingId(Long mealsTrackingId);
    List<MealFood> findMealFoodByFoodId(Long foodId);
    MealFood findByMealsTrackingIdAndFoodId(Long mealstrackingid, Long foodId);
    void deleteByMealsTrackingIdAndFoodId(Long mealstrackingid, Long foodId);

}
