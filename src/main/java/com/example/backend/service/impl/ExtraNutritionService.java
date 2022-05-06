//package com.example.backend.service.impl;
//
//import com.example.backend.model.ExtraNutrition;
//import com.example.backend.repository.ExtraNutritionRepository;
//import com.example.backend.service.IExtraNutritionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ExtraNutritionService implements IExtraNutritionService {
//    @Autowired
//    public ExtraNutritionRepository extraNutritionRepository;
//
//    @Override
//    public List<ExtraNutrition> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<ExtraNutrition> findAllByFoodId(Long foodId) {
//        return extraNutritionRepository.findExtraNutritionByFoodId(foodId);
//    }
//
//    @Override
//    public List<ExtraNutrition> findAllByNutrientsId(Long nutrientId) {
//        return extraNutritionRepository.findExtraNutritionByNutrientsId(nutrientId);
//    }
//}
