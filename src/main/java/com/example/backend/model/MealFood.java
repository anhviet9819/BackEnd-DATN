package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "meal_food")
@JsonIgnoreProperties(value = "mealsTrackingId")
public class MealFood {
    @EmbeddedId
    private MealFoodId id;

    @NotNull
    @Column(name = "food_volume")
    private Double food_volume;

    @ManyToOne
    @MapsId("mealTrackingId")
    @JoinColumn(name = "meal_tracking_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    MealsTracking mealsTracking;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Food food;

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

    public MealFood(MealFoodId id, Double food_volume, MealsTracking mealsTracking, Food food) {
        this.id = id;
        this.food_volume = food_volume;
        this.mealsTracking = mealsTracking;
        this.food = food;
    }

    public MealFood(MealsTracking mealsTracking, Food food) {
        this.mealsTracking = mealsTracking;
        this.food = food;
    }

    public MealFood(MealFoodId id, MealsTracking mealsTracking, Food food) {
        this.id = id;
        this.mealsTracking = mealsTracking;
        this.food = food;
    }

    public MealFood(MealFoodId id, Double food_volume, MealsTracking mealsTracking, Food food, Double calories, Double fat, Double saturated_fat, Double trans_fat, Double protein, Double cholesterol, Double sodium, Double potassium, Double carbohydrates, Double diatery_fiber, Double sugars, Double vitamin_a, Double vitamin_c, Double calcium, Double iron) {
        this.id = id;
        this.food_volume = food_volume;
        this.mealsTracking = mealsTracking;
        this.food = food;
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
    }

    public MealFoodId getId() {
        return id;
    }

    public void setId(MealFoodId id) {
        this.id = id;
    }

    public Double getFood_volume() {
        return food_volume;
    }

    public void setFood_volume(Double food_volume) {
        this.food_volume = food_volume;
    }

    public MealsTracking getMealsTracking() {
        return mealsTracking;
    }

    public void setMealsTracking(MealsTracking mealsTracking) {
        this.mealsTracking = mealsTracking;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public MealFood() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealFood mealFood = (MealFood) o;
        return Objects.equals(id, mealFood.id) && Objects.equals(food_volume, mealFood.food_volume) && Objects.equals(mealsTracking, mealFood.mealsTracking) && Objects.equals(food, mealFood.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, food_volume, mealsTracking, food);
    }
}
