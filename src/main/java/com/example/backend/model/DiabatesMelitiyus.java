package com.example.backend.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "diabates_melitiyus")
public class DiabatesMelitiyus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Double blood_glucose_before_meal;
    private Double blood_glucose_after_meal;

    @CreatedDate
    private Instant created_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_tracking_id", referencedColumnName = "id")
    private UsersTracking usersTracking;

    public DiabatesMelitiyus() {
    }

    public DiabatesMelitiyus(Double blood_glucose_before_meal, Double blood_glucose_after_meal, Instant created_at, UsersTracking usersTracking) {
        this.blood_glucose_before_meal = blood_glucose_before_meal;
        this.blood_glucose_after_meal = blood_glucose_after_meal;
        this.created_at = created_at;
        this.usersTracking = usersTracking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBlood_glucose_before_meal() {
        return blood_glucose_before_meal;
    }

    public void setBlood_glucose_before_meal(Double blood_glucose_before_meal) {
        this.blood_glucose_before_meal = blood_glucose_before_meal;
    }

    public Double getBlood_glucose_after_meal() {
        return blood_glucose_after_meal;
    }

    public void setBlood_glucose_after_meal(Double blood_glucose_after_meal) {
        this.blood_glucose_after_meal = blood_glucose_after_meal;
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
}
