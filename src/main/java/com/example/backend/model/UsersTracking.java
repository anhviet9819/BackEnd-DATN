package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users_tracking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsersTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean is_diabates_meltiyus;
    private Boolean is_blood_pressure_diseases;
    private Boolean is_heart_diseases;
    private Double current_height;
    private Double current_weight;
    private Integer current_diastolic;
    private Integer current_systolic;
    private Double current_blood_before_meal;
    private Double current_normal_blood;

//    @OneToMany(mappedBy="usersTracking")
//    private Set<MealsTracking> mealsTrackings;

    public UsersTracking(Long id, Boolean is_diabates_meltiyus, Boolean is_blood_pressure_diseases, Boolean is_heart_diseases, Double current_height, Double current_weight, Integer current_diastolic, Integer current_systolic, Double current_blood_before_meal, Double current_normal_blood) {
        this.id = id;
        this.is_diabates_meltiyus = is_diabates_meltiyus;
        this.is_blood_pressure_diseases = is_blood_pressure_diseases;
        this.is_heart_diseases = is_heart_diseases;
        this.current_height = current_height;
        this.current_weight = current_weight;
        this.current_diastolic = current_diastolic;
        this.current_systolic = current_systolic;
        this.current_blood_before_meal = current_blood_before_meal;
        this.current_normal_blood = current_normal_blood;
    }

    public UsersTracking(Long id) {
        this.id = id;
    }

    public UsersTracking(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIs_diabates_meltiyus() {
        return is_diabates_meltiyus;
    }

    public void setIs_diabates_meltiyus(Boolean is_diabates_meltiyus) {
        this.is_diabates_meltiyus = is_diabates_meltiyus;
    }

    public Boolean getIs_blood_pressure_diseases() {
        return is_blood_pressure_diseases;
    }

    public void setIs_blood_pressure_diseases(Boolean is_blood_pressure_diseases) {
        this.is_blood_pressure_diseases = is_blood_pressure_diseases;
    }

    public Boolean getIs_heart_diseases() {
        return is_heart_diseases;
    }

    public void setIs_heart_diseases(Boolean is_heart_diseases) {
        this.is_heart_diseases = is_heart_diseases;
    }

    public Double getCurrent_height() {
        return current_height;
    }

    public void setCurrent_height(Double current_height) {
        this.current_height = current_height;
    }

    public Double getCurrent_weight() {
        return current_weight;
    }

    public void setCurrent_weight(Double current_weight) {
        this.current_weight = current_weight;
    }

    public Integer getCurrent_diastolic() {
        return current_diastolic;
    }

    public void setCurrent_diastolic(Integer current_diastolic) {
        this.current_diastolic = current_diastolic;
    }

    public Integer getCurrent_systolic() {
        return current_systolic;
    }

    public void setCurrent_systolic(Integer current_systolic) {
        this.current_systolic = current_systolic;
    }

    public Double getCurrent_blood_before_meal() {
        return current_blood_before_meal;
    }

    public void setCurrent_blood_before_meal(Double current_blood_before_meal) {
        this.current_blood_before_meal = current_blood_before_meal;
    }

    public Double getCurrent_normal_blood() {
        return current_normal_blood;
    }

    public void setCurrent_normal_blood(Double current_normal_blood) {
        this.current_normal_blood = current_normal_blood;
    }
}
