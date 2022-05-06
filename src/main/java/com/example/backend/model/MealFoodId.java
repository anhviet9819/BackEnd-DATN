package com.example.backend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MealFoodId implements Serializable {
    @Column(name = "food_id")
    private Long food_id;

    @Column(name = "meal_tracking_id")
    private Long meal_tracking_id;

    public MealFoodId() {

    }

    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public Long getMeal_tracking_id() {
        return meal_tracking_id;
    }

    public void setMeal_tracking_id(Long meal_tracking_id) {
        this.meal_tracking_id = meal_tracking_id;
    }

    public MealFoodId(Long food_id, Long meal_tracking_id) {
        this.food_id = food_id;
        this.meal_tracking_id = meal_tracking_id;
    }


}
