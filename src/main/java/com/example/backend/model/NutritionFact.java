package com.example.backend.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "nutrition_fact")
public class NutritionFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serving_unit;

    private Double serving_weight_grams;

    private Double calories;

    private Double fat;

    private Double saturated_fat;

    private Double trans_fat;

    private Double protein;

    private Double cholesterol;

    private Double sodium;

    private Double potassium;

    private Double carbohydrates;

    private Double diatery_fiber;

    private Double sugars;

    private Double vitamin_a;

    private Double vitamin_c;

    private Double calcium;

    private Double iron;

    private Instant updated_at;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Food food;

    public NutritionFact() {
    }

    public NutritionFact(Food food) {
        this.food = food;
    }



    public NutritionFact(Long id, String serving_unit, Double serving_weight_grams, Double calories, Double fat, Double saturated_fat, Double trans_fat, Double protein, Double cholesterol, Double sodium, Double potassium, Double carbohydrates, Double diatery_fiber, Double sugars, Double vitamin_a, Double vitamin_c, Double calcium, Double iron, Instant updated_at, Food food) {
        this.id = id;
        this.serving_unit = serving_unit;
        this.serving_weight_grams = serving_weight_grams;
        this.calories = calories;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.trans_fat = trans_fat;
        this.protein = protein;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.potassium = potassium;
        this.carbohydrates = carbohydrates;
        this.diatery_fiber = diatery_fiber;
        this.sugars = sugars;
        this.vitamin_a = vitamin_a;
        this.vitamin_c = vitamin_c;
        this.calcium = calcium;
        this.iron = iron;
        this.updated_at = updated_at;
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServing_unit() {
        return serving_unit;
    }

    public void setServing_unit(String serving_unit) {
        this.serving_unit = serving_unit;
    }

    public Double getServing_weight_grams() {
        return serving_weight_grams;
    }

    public void setServing_weight_grams(Double serving_weight_grams) {
        this.serving_weight_grams = serving_weight_grams;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(Double saturated_fat) {
        this.saturated_fat = saturated_fat;
    }

    public Double getTrans_fat() {
        return trans_fat;
    }

    public void setTrans_fat(Double trans_fat) {
        this.trans_fat = trans_fat;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getDiatery_fiber() {
        return diatery_fiber;
    }

    public void setDiatery_fiber(Double diatery_fiber) {
        this.diatery_fiber = diatery_fiber;
    }

    public Double getSugars() {
        return sugars;
    }

    public void setSugars(Double sugars) {
        this.sugars = sugars;
    }

    public Double getVitamin_a() {
        return vitamin_a;
    }

    public void setVitamin_a(Double vitamin_a) {
        this.vitamin_a = vitamin_a;
    }

    public Double getVitamin_c() {
        return vitamin_c;
    }

    public void setVitamin_c(Double vitamin_c) {
        this.vitamin_c = vitamin_c;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
