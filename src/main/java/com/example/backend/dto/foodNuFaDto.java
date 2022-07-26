package com.example.backend.dto;

public class foodNuFaDto {
    private Long foodId;
    private String foodName;
    private Double foodCalories;
    private Double foodIron;

    public foodNuFaDto(Long foodId, String foodName, Double foodCalories, Double foodIron) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodCalories = foodCalories;
        this.foodIron = foodIron;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(Double foodCalories) {
        this.foodCalories = foodCalories;
    }

    public Double getFoodIron() {
        return foodIron;
    }

    public void setFoodIron(Double foodIron) {
        this.foodIron = foodIron;
    }
}
