package com.example.backend.dto;

import com.example.backend.model.Food;
import com.example.backend.model.NutritionFact;

public class MealNutritionDTO {
    private Food food;

    private NutritionFact nutritionFact;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public NutritionFact getNutritionFact() {
        return nutritionFact;
    }

    public void setNutritionFact(NutritionFact nutritionFact) {
        this.nutritionFact = nutritionFact;
    }

    public MealNutritionDTO(Food food, NutritionFact nutritionFact) {
        this.food = food;
        this.nutritionFact = nutritionFact;
    }
}
