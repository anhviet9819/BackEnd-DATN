package com.example.backend.repository;

import com.example.backend.model.ExtraNutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraNutritionRepository extends JpaRepository<ExtraNutrition, Long> {
//    List<ExtraNutrition> findExtraNutritionByFoodId(Long foodId);
//    List<ExtraNutrition> findExtraNutritionByNutrientsId(Long nutrientId);
}
