package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "activities_tracking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ActivitiesTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double calo_loss;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "start_time")
    private Instant startTime;
    private Instant end_time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_tracking_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersTracking usersTracking;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_activity_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ListActivities listActivities;

    public ActivitiesTracking(Long id, Double calo_loss, Instant created_at, Instant start_time, Instant end_time, UsersTracking usersTracking) {
        this.id = id;
        this.calo_loss = calo_loss;
        this.createdAt = created_at;
        this.startTime = start_time;
        this.end_time = end_time;
        this.usersTracking = usersTracking;
    }

    public ActivitiesTracking() {
    }

    public ActivitiesTracking(Instant created_at, Instant start_time, Instant end_time, UsersTracking usersTracking) {
        this.createdAt = created_at;
        this.startTime = start_time;
        this.end_time = end_time;
        this.usersTracking = usersTracking;
    }

    public ActivitiesTracking(Double calo_loss, Instant created_at, Instant start_time, Instant end_time, UsersTracking usersTracking, ListActivities listActivities) {
        this.calo_loss = calo_loss;
        this.createdAt = created_at;
        this.startTime = start_time;
        this.end_time = end_time;
        this.usersTracking = usersTracking;
        this.listActivities = listActivities;
    }

    public ActivitiesTracking(Instant created_at, Instant start_time, Instant end_time) {
        this.createdAt = created_at;
        this.startTime = start_time;
        this.end_time = end_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCalo_loss() {
        return calo_loss;
    }

    public void setCalo_loss(Double calo_loss) {
        this.calo_loss = calo_loss;
    }

    public Instant getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Instant created_at) {
        this.createdAt = created_at;
    }

    public Instant getStart_time() {
        return startTime;
    }

    public void setStart_time(Instant start_time) {
        this.startTime = start_time;
    }

    public Instant getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Instant end_time) {
        this.end_time = end_time;
    }

    public UsersTracking getUsersTracking() {
        return usersTracking;
    }

    public void setUsersTracking(UsersTracking usersTracking) {
        this.usersTracking = usersTracking;
    }

    public ListActivities getListActivities() {
        return listActivities;
    }

    public void setListActivities(ListActivities listActivities) {
        this.listActivities = listActivities;
    }
}
