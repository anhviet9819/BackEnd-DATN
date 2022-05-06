package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "nutrients")
public class Nutrients {
    @Id
    private Long id;

    private String nutrient;
    private String unit;
    private String tag_name;

    @OneToMany(mappedBy = "nutrients", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExtraNutrition> extraNutritions = new ArrayList<>();

    public Nutrients(Long id, String nutrient, String unit, String tag_name) {
        this.id = id;
        this.nutrient = nutrient;
        this.unit = unit;
        this.tag_name = tag_name;
    }

    public Nutrients() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNutrient() {
        return nutrient;
    }

    public void setNutrient(String nutrient) {
        this.nutrient = nutrient;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
