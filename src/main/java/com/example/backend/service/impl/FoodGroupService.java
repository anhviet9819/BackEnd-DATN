package com.example.backend.service.impl;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.FoodGroup;
import com.example.backend.repository.FoodGroupRepository;
import com.example.backend.service.IFoodGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodGroupService implements IFoodGroupService {
    @Autowired
    private FoodGroupRepository foodGroupRepository;

//    @Override
//    public List<FoodGroup> queryByTen(String ten) {
//        if(ten == null) return foodGroupRepository.findAll();
//        List<FoodGroup> foodGroup = foodGroupRepository.findByTenContaining(ten);
//        if(foodGroup.size() == 0) throw new ResourceNotFoundException("FoodGroup");
//
//        return foodGroup;
//    }

    @Override
    public List<FoodGroup> queryAll() {
        return foodGroupRepository.findAll();
    }

    @Override
    public FoodGroup getOneById(Long id) throws ResourceNotFoundException{
        FoodGroup foodGroup = foodGroupRepository.findById(id).orElse(null);
        if(foodGroup == null) throw new ResourceNotFoundException("FoodGroup", "id", id);
        return foodGroup;
    }

    @Override
    public FoodGroup save(FoodGroup foodGroup) {
        return foodGroupRepository.save(foodGroup);
    }

    @Override
    public void delete(Long id) {
        foodGroupRepository.deleteById(id);
    }
}
