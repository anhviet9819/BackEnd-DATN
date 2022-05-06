package com.example.backend.model;

public class FoodVolume {
    private Food food;
    private Double food_volume;

    public Food getFood() {
        return food;
    }

    public Double getFood_volume() {
        return food_volume;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setFood_volume(Double food_volume) {
        this.food_volume = food_volume;
    }
}
