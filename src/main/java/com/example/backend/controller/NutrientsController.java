package com.example.backend.controller;

import com.example.backend.model.Nutrients;
import com.example.backend.service.INutrientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nutrients")
public class NutrientsController {
    @Autowired
    INutrientsService nutrientsService;

    @GetMapping("/search")
    public List<Nutrients> findAllNutrients(){
        return nutrientsService.findAll();
    }

    @GetMapping("/details/nutrientsname/{nutrientsname}")
    public List<Nutrients> findNutrientByNutrientName(@PathVariable("nutrientsname") String nutrientsname){
        return nutrientsService.findByNutrient(nutrientsname);
    }

//    @GetMapping("/details/tagname/{tagname}")
//    public Nutrients findNutrientByTagname(@PathVariable("tagname")String tagname){
//        return nutrientsService.findByTagName(tagname);
//    }
}
