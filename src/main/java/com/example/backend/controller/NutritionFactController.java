package com.example.backend.controller;

import com.example.backend.model.Food;
import com.example.backend.model.NutritionFact;
import com.example.backend.service.IFoodService;
import com.example.backend.service.INutritionFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritionfact")
public class NutritionFactController {
    @Autowired
    public INutritionFactService nutritionFactService;

    @Autowired
    public IFoodService foodService;

    @GetMapping("/search")
    public List<NutritionFact> getAll(){
        return nutritionFactService.findAll();
    }

    @GetMapping("/details/food/{foodid}")
    public NutritionFact findNutritionFactByFoodId(@PathVariable("foodid")Long foodId){
        return nutritionFactService.findByFoodId(foodId);
    }

    @PostMapping("/create/food/{foodid}")
    public NutritionFact createNutritionFactByFoodId(@PathVariable("foodid")Long foodId,
                                                     @Validated @RequestBody NutritionFact nutritionFact){
        Food food = foodService.findById(foodId);

        return nutritionFactService.createNutritionFact(nutritionFact);
    }

    @DeleteMapping("/delete/food/{foodid}")
    public ResponseEntity<?> deleteNutritionFactByFoodid(@PathVariable("foodid")Long foodid){
        nutritionFactService.deleteNutritonFactByFoodId(foodid);
        return ResponseEntity.ok().build();
    }
}
