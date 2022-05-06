package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "list_activities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ListActivities {
    @Id
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private Double calo_per_seconds;

    public ListActivities(Long id, String name, Double calo_per_seconds) {
        this.id = id;
        this.name = name;
        this.calo_per_seconds = calo_per_seconds;
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

    public Double getCalo_per_seconds() {
        return calo_per_seconds;
    }

    public void setCalo_per_seconds(Double calo_per_seconds) {
        this.calo_per_seconds = calo_per_seconds;
    }
}
