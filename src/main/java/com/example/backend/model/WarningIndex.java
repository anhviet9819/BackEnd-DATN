package com.example.backend.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "warning_index")
public class WarningIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "high_weight")
    private Double highWeight;
    @Column(name = "low_weight")
    private Double lowWeight;
    @Column(name = "high_glycemic")
    private Double highGlycemic;
    @Column(name = "low_glycemic")
    private Double lowGlycemic;
    @Column(name = "high_systolic")
    private Integer highSystolic;
    @Column(name = "high_diastolic")
    private Integer highDiastolic;
    @Column(name = "low_systolic")
    private Integer lowSystolic;
    @Column(name = "low_diastolic")
    private Integer lowDiastolic;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_tracking_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersTracking usersTracking;

    public WarningIndex() {
    }

    public WarningIndex(UsersTracking usersTracking) {
        this.usersTracking = usersTracking;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHighWeight() {
        return highWeight;
    }

    public void setHighWeight(Double highWeight) {
        this.highWeight = highWeight;
    }

    public Double getLowWeight() {
        return lowWeight;
    }

    public void setLowWeight(Double lowWeight) {
        this.lowWeight = lowWeight;
    }

    public Double getHighGlycemic() {
        return highGlycemic;
    }

    public void setHighGlycemic(Double highGlycemic) {
        this.highGlycemic = highGlycemic;
    }

    public Double getLowGlycemic() {
        return lowGlycemic;
    }

    public Integer getHighSystolic() {
        return highSystolic;
    }

    public void setHighSystolic(Integer highSystolic) {
        this.highSystolic = highSystolic;
    }

    public Integer getHighDiastolic() {
        return highDiastolic;
    }

    public void setHighDiastolic(Integer highDiastolic) {
        this.highDiastolic = highDiastolic;
    }

    public Integer getLowSystolic() {
        return lowSystolic;
    }

    public void setLowSystolic(Integer lowSystolic) {
        this.lowSystolic = lowSystolic;
    }

    public Integer getLowDiastolic() {
        return lowDiastolic;
    }

    public void setLowDiastolic(Integer lowDiastolic) {
        this.lowDiastolic = lowDiastolic;
    }

    public void setLowGlycemic(Double lowGlycemic) {
        this.lowGlycemic = lowGlycemic;
    }

    public UsersTracking getUsersTracking() {
        return usersTracking;
    }

    public void setUsersTracking(UsersTracking usersTracking) {
        this.usersTracking = usersTracking;
    }
}
