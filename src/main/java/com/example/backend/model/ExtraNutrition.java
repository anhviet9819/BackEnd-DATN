package com.example.backend.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity(name = "extra_nutrition")
public class ExtraNutrition {
    @EmbeddedId
    private ExtraNutritionId id;

    private Double value;

    private Instant updated_at;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    Food food1;

    @ManyToOne
    @MapsId("nutrientId")
    @JoinColumn(name = "nutrient_id")
    Nutrients nutrients;

    public ExtraNutrition() {
    }

    public ExtraNutrition(ExtraNutritionId id, Double value, Instant updated_at, Food food, Nutrients nutrients) {
        this.id = id;
        this.value = value;
        this.updated_at = updated_at;
        this.food1 = food;
        this.nutrients = nutrients;
    }

    public ExtraNutritionId getId() {
        return id;
    }

    public void setId(ExtraNutritionId id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public Food getFood() {
        return food1;
    }

    public void setFood(Food food) {
        this.food1 = food;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraNutrition that = (ExtraNutrition) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value) && Objects.equals(updated_at, that.updated_at) && Objects.equals(food1, that.food1) && Objects.equals(nutrients, that.nutrients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, updated_at, food1, nutrients);
    }
}
