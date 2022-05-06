package com.example.backend.service;

import com.example.backend.model.FoodGroup;

import java.util.List;

public interface IFoodGroupService {
//    List<FoodGroup> queryByTen(String ten);
    List<FoodGroup> queryAll();
    FoodGroup getOneById(Long id);
    FoodGroup save(FoodGroup foodGroup);
    void delete(Long id);
}
