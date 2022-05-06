package com.example.backend.repository;

import com.example.backend.model.NutritionFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionFactRepository extends JpaRepository<NutritionFact,Long> {
    NutritionFact findNutritionFactByFoodId(Long foodId);

    NutritionFact deleteNutritionFactByFoodId(Long foodId);
}
