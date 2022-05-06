package com.example.backend.repository;

import com.example.backend.model.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
    List<Nutrients> findByNutrient(String nutrient);
//    Nutrients findByTag_name(String tag_name);
}
