package com.example.backend.service.impl;

import com.example.backend.model.Nutrients;
import com.example.backend.repository.NutrientsRepository;
import com.example.backend.service.INutrientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutrientsService implements INutrientsService {
    @Autowired
    NutrientsRepository nutrientsRepository;

    @Override
    public List<Nutrients> findAll() {
        return nutrientsRepository.findAll();
    }

    @Override
    public List<Nutrients> findByNutrient(String nutrient) {
        return nutrientsRepository.findByNutrient(nutrient);
    }

//    @Override
//    public Nutrients findByTagName(String tagName) {
//        return nutrientsRepository.findByTag_name(tagName);
//    }
}
