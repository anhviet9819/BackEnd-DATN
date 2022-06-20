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

    @NotNull
    private String name;

    private String description;
    private String type;
    private Double meal_volume;

    @CreatedDate
    @NotNull
    private Instant created_at;

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
        this.created_at = created_at;
    }

    public MealsTracking(String name, String description, String type, Double meal_volume, Instant created_at, UsersTracking usersTracking) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.meal_volume = meal_volume;
        this.created_at = created_at;
        this.usersTracking = usersTracking;
    }

    public MealsTracking(String name, String description, String type, Double meal_volume, Instant created_at) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.meal_volume = meal_volume;
        this.created_at = created_at;
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
        this.created_at = created_at;
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

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
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
