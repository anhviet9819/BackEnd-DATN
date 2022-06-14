package com.example.backend.repository;

import com.example.backend.model.Food;
import com.example.backend.model.FoodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findFoodByFoodGroupId(Long FoodGroupId);
//    List<Food> findFoodByFoodGroupName(String foodGroupName);
//    List<Food> findFoodByMealsTrackingId(Long mealsTrackingId);
    Food findFoodByNameContaining(String foodName);
    Long countFoodByFoodGroupId(Long FoodGroupId);
    Page<Food> findAllFoodByNameContainingOrderById(Pageable pageable, String nameContaining);
}
