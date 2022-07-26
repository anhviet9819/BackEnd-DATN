package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

@Entity(name = "list_activities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ListActivities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    @Column(name = "calo_per_hour")
    private Double caloPerHour;
    private Boolean owner;
    private String scope;

    public ListActivities(Long id, String name, Double calo_per_hour, Double calo_per_seconds) {
        this.id = id;
        this.name = name;
        this.caloPerHour = calo_per_hour;
    }

    public ListActivities(String name, Double calo_per_hour, Boolean owner, String scope) {
        this.name = name;
        this.caloPerHour = calo_per_hour;
        this.owner = owner;
        this.scope = scope;
    }

    public ListActivities(String name, Double calo_per_hour) {
        this.name = name;
        this.caloPerHour = calo_per_hour;
    }

    public ListActivities() {

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

    public Double getCalo_per_hour() {
        return caloPerHour;
    }

    public void setCalo_per_hour(Double calo_per_hour) {
        this.caloPerHour = calo_per_hour;
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
}
