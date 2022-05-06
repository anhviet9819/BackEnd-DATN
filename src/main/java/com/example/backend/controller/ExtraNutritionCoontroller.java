//package com.example.backend.controller;
//
//import com.example.backend.model.ExtraNutrition;
//import com.example.backend.service.IExtraNutritionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/extranutrition")
//public class ExtraNutritionCoontroller {
//    @Autowired
//    IExtraNutritionService extraNutritionService;
//
//    @GetMapping("/search")
//    public List<ExtraNutrition> findAllExtraNutrition(){
//        return extraNutritionService.findAll();
//    }
//
//    @GetMapping("/details/food/{foodid}")
//    public List<ExtraNutrition> findAllByFoodId(@PathVariable("foodid")Long foodId){
//        return extraNutritionService.findAllByFoodId(foodId);
//    }
//
//    @GetMapping("/details/nutrientid/{nutrientid}")
//    public List<ExtraNutrition> findAllByNutrientId(@PathVariable("nutrientid")Long nutrientId){
//        return extraNutritionService.findAllByNutrientsId(nutrientId);
//    }
//}
