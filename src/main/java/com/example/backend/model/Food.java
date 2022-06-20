package com.example.backend.model;

import com.example.backend.service.IFoodGroupService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "food")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Food {
    @Id
    private Long id;

    @NotNull
    private String name;

    private String image;
    private String language;
    private Boolean owner;
    private String scope;
    private Instant created_at;
    private LocalTime updated_at;
    private Boolean is_ingredient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FoodGroup foodGroup;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "foods")
//    @JsonIgnore
//    private Set<MealsTracking> mealsTracking = new HashSet<>();

    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private NutritionFact nutritionFact;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MealFood> mealFood = new ArrayList<>();

    @OneToMany(mappedBy = "food1", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExtraNutrition> extraNutritions = new ArrayList<>();

    public Food(Long id, String name, String image, String language, Boolean owner, String scope, Instant created_at, LocalTime updated_at, Boolean is_ingredient, FoodGroup foodGroup) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.language = language;
        this.owner = owner;
        this.scope = scope;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_ingredient = is_ingredient;
        this.foodGroup = foodGroup;
    }

    public Food(String name, Instant created_at, FoodGroup foodGroup) {
        this.name = name;
        this.created_at = created_at;
        this.foodGroup = foodGroup;
    }

    public Food(Long id, String name, Instant created_at, FoodGroup foodGroup) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.foodGroup = foodGroup;
    }

    public Food(Long id, String name, Boolean owner, String scope, Instant created_at, FoodGroup foodGroup) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.scope = scope;
        this.created_at = created_at;
        this.foodGroup = foodGroup;
    }

    public Food() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public LocalTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalTime updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getIs_ingredient() {
        return is_ingredient;
    }

    public void setIs_ingredient(Boolean is_ingredient) {
        this.is_ingredient = is_ingredient;
    }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) { this.foodGroup = foodGroup; }

    public List<MealFood> getMealFood() {
        return mealFood;
    }

    public void setMealFoods(List<MealFood> mealFoods) {
        this.mealFood = mealFood;
    }

    //    public Set<MealsTracking> getMealsTracking() {
//        return mealsTracking;
//    }

//    public void setMealsTracking(Set<MealsTracking> mealsTracking) {
//        this.mealsTracking = mealsTracking;
//    }
}
