package com.example.backend.service;

import com.example.backend.model.Nutrients;

import java.util.List;

public interface INutrientsService {
    List<Nutrients> findAll();
    List<Nutrients> findByNutrient(String nutrient);
//    Nutrients findByTagName(String tagName);
}
