package com.example.backend.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity(name="blood_pressure")
public class BloodPressure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Integer diastolic;
    private Integer systolic;

    @CreatedDate
    private Instant created_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_tracking_id", referencedColumnName = "id")
    private UsersTracking usersTracking;

    public BloodPressure() {
    }

    public BloodPressure(Integer diastolic, Integer systolic, Instant created_at, UsersTracking usersTracking) {
        this.diastolic = diastolic;
        this.systolic = systolic;
        this.created_at = created_at;
        this.usersTracking = usersTracking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
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
