package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

@Entity(name = "meals_tracking")
@JsonIgnoreProperties(value = "mealFoods")
public class MealsTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    private String type;
    private Double meal_volume;
    private Double meal_calories;
    private Double meal_fat;
    private Double meal_trans_fat;
    private Double meal_saturated_fat;
    private Double meal_cholesterol;
    private Double meal_diatery_fiber;
    private Double meal_sodium;
    private Double meal_protein;
    private Double meal_potassium;
    private Double meal_carbohydrates;
    private Double meal_sugars;
    private Double meal_vitamin_a;
    private Double meal_vitamin_c;
    private Double meal_calcium;
    private Double meal_iron;

    @CreatedDate
    @NotNull
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_tracking_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersTracking usersTracking;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "meal_food",
//            joinColumns = { @JoinColumn(name = "meal_tracking_id") },
//            inverseJoinColumns = { @JoinColumn(name = "food_id") })
//    private Set<Food> foods = new HashSet<>();

    @OneToMany(mappedBy = "mealsTracking", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MealFood> mealFoods = new ArrayList<MealFood>();

    public MealsTracking() {
    }

    public MealsTracking(Long id) {
        this.id = id;
    }

    public MealsTracking(Long id, String name, String description, String type, Double meal_volume, Instant created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.meal_volume = meal_volume;
        this.createdAt = created_at;
    }

    public MealsTracking(String name, String description, String type, Double meal_volume, Instant created_at, UsersTracking usersTracking) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.meal_volume = meal_volume;
        this.createdAt = created_at;
        this.usersTracking = usersTracking;
    }

    public MealsTracking(String name, String description, String type, Double meal_volume, Instant created_at) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.meal_volume = meal_volume;
        this.createdAt = created_at;
    }

    public List<MealFood> getMealFoods() {
        return mealFoods;
    }

    public void setMealFoods(List<MealFood> mealFoods) {
        this.mealFoods = mealFoods;
    }

    public MealsTracking(Long id, String name, String description, String type, Double meal_volume, Instant created_at, UsersTracking usersTracking, List<MealFood> mealFoods) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.meal_volume = meal_volume;
        this.createdAt = created_at;
        this.usersTracking = usersTracking;
        this.mealFoods = mealFoods;
    }

    public MealsTracking(Long id, String name, String description, String type, Double meal_volume, Double meal_calories, Double meal_fat, Double meal_trans_fat, Double meal_saturated_fat, Double meal_cholesterol, Double meal_diatery_fiber, Double meal_sodium, Double meal_protein, Double meal_potassium, Double meal_carbohydrates, Double meal_sugars, Double meal_vitamin_a, Double meal_vitamin_c, Double meal_calcium, Double meal_iron, Instant createdAt, UsersTracking usersTracking, List<MealFood> mealFoods) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.meal_volume = meal_volume;
        this.meal_calories = meal_calories;
        this.meal_fat = meal_fat;
        this.meal_trans_fat = meal_trans_fat;
        this.meal_saturated_fat = meal_saturated_fat;
        this.meal_cholesterol = meal_cholesterol;
        this.meal_diatery_fiber = meal_diatery_fiber;
        this.meal_sodium = meal_sodium;
        this.meal_protein = meal_protein;
        this.meal_potassium = meal_potassium;
        this.meal_carbohydrates = meal_carbohydrates;
        this.meal_sugars = meal_sugars;
        this.meal_vitamin_a = meal_vitamin_a;
        this.meal_vitamin_c = meal_vitamin_c;
        this.meal_calcium = meal_calcium;
        this.meal_iron = meal_iron;
        this.createdAt = createdAt;
        this.usersTracking = usersTracking;
        this.mealFoods = mealFoods;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMeal_volume() {
        return meal_volume;
    }

    public void setMeal_volume(Double meal_volume) {
        this.meal_volume = meal_volume;
    }

    public Double getMeal_fat() {
        return meal_fat;
    }

    public void setMeal_fat(Double meal_fat) {
        this.meal_fat = meal_fat;
    }

    public Double getMeal_trans_fat() {
        return meal_trans_fat;
    }

    public void setMeal_trans_fat(Double meal_trans_fat) {
        this.meal_trans_fat = meal_trans_fat;
    }

    public Double getMeal_saturated_fat() {
        return meal_saturated_fat;
    }

    public void setMeal_saturated_fat(Double meal_saturated_fat) {
        this.meal_saturated_fat = meal_saturated_fat;
    }

    public Double getMeal_cholesterol() {
        return meal_cholesterol;
    }

    public void setMeal_cholesterol(Double meal_cholesterol) {
        this.meal_cholesterol = meal_cholesterol;
    }

    public Double getMeal_diatery_fiber() {
        return meal_diatery_fiber;
    }

    public void setMeal_diatery_fiber(Double meal_diatery_fiber) {
        this.meal_diatery_fiber = meal_diatery_fiber;
    }

    public Double getMeal_sodium() {
        return meal_sodium;
    }

    public void setMeal_sodium(Double meal_sodium) {
        this.meal_sodium = meal_sodium;
    }

    public Double getMeal_protein() {
        return meal_protein;
    }

    public void setMeal_protein(Double meal_protein) {
        this.meal_protein = meal_protein;
    }

    public Double getMeal_potassium() {
        return meal_potassium;
    }

    public void setMeal_potassium(Double meal_potassium) {
        this.meal_potassium = meal_potassium;
    }

    public Double getMeal_carbohydrates() {
        return meal_carbohydrates;
    }

    public void setMeal_carbohydrates(Double meal_carbohydrates) {
        this.meal_carbohydrates = meal_carbohydrates;
    }

    public Double getMeal_sugars() {
        return meal_sugars;
    }

    public void setMeal_sugars(Double meal_sugars) {
        this.meal_sugars = meal_sugars;
    }

    public Double getMeal_vitamin_a() {
        return meal_vitamin_a;
    }

    public void setMeal_vitamin_a(Double meal_vitamin_a) {
        this.meal_vitamin_a = meal_vitamin_a;
    }

    public Double getMeal_vitamin_c() {
        return meal_vitamin_c;
    }

    public void setMeal_vitamin_c(Double meal_vitamin_c) {
        this.meal_vitamin_c = meal_vitamin_c;
    }

    public Double getMeal_calcium() {
        return meal_calcium;
    }

    public void setMeal_calcium(Double meal_calcium) {
        this.meal_calcium = meal_calcium;
    }

    public Double getMeal_iron() {
        return meal_iron;
    }

    public void setMeal_iron(Double meal_iron) {
        this.meal_iron = meal_iron;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Instant created_at) {
        this.createdAt = created_at;
    }

    public UsersTracking getUsersTracking() {
        return usersTracking;
    }

    public void setUsersTracking(UsersTracking usersTracking) {
        this.usersTracking = usersTracking;
    }

    public void addFood(MealFood mealFood) {
        this.mealFoods.add(mealFood);
//        mealFood.getMealsTracking().addFood(mealFood);
    }

    public Double getMeal_calories() {
        return meal_calories;
    }

    public void setMeal_calories(Double meal_calories) {
        this.meal_calories = meal_calories;
    }

    //    public Double sumMealVolume(){
//
//    }
//
//    public void removeFood(Long foodId) {
//        Food food = this.foods.stream().filter(t -> t.getId() == foodId).findFirst().orElse(null);
//        if (food != null) this.foods.remove(food);
//        food.getMealsTracking().remove(this);
//    }

}
