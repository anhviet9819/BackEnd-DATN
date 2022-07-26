package com.example.backend.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(name = "personal_index")
public class PersonalIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double height;
    private Double weight;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_tracking_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersTracking usersTracking;

    public PersonalIndex() {

    }

    public PersonalIndex(Long id) {
        this.id = id;
    }

    public PersonalIndex(Long id, Double height, Double weight, Instant created_at, UsersTracking usersTracking) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.createdAt = created_at;
        this.usersTracking = usersTracking;
    }

    public PersonalIndex(Double height, Double weight, UsersTracking usersTracking, Instant created_at) {
//        this.id = id;
        this.height = height;
        this.weight = weight;
        this.usersTracking = usersTracking;
        this.createdAt = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
}
