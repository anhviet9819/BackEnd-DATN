package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "meal_food")
@JsonIgnoreProperties(value = "mealsTrackingId")
public class MealFood {
    @EmbeddedId
    private MealFoodId id;

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
