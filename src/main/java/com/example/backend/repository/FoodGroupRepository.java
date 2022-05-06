package com.example.backend.repository;

import com.example.backend.model.Food;
import com.example.backend.model.FoodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodGroupRepository extends JpaRepository<FoodGroup, Long> {
    FoodGroup findByNameContaining(String name);
}
