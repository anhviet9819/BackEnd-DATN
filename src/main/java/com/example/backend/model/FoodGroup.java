package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "food_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FoodGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_name")
    private String name;

    public FoodGroup(Long id, String group_name) {
        this.id = id;
        this.name = group_name;
    }

    public FoodGroup() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup_name() {
        return name;
    }

    public void setGroup_name(String group_name) {
        this.name = group_name;
    }
}
