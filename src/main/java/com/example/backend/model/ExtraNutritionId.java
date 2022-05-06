package com.example.backend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ExtraNutritionId implements Serializable {
    @Column(name = "food_id")
    private Long food_id;

    @Column(name = "nutrient_id")
    private Long nutrient_id;

    public ExtraNutritionId() {
    }

    public ExtraNutritionId(Long food_id, Long nutrient_id) {
        this.food_id = food_id;
        this.nutrient_id = nutrient_id;
    }

    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public Long getNutrient_id() {
        return nutrient_id;
    }

    public void setNutrient_id(Long nutrient_id) {
        this.nutrient_id = nutrient_id;
    }
}
