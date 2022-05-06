package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.model.FoodGroup;
import com.example.backend.service.IFoodGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodgroup")
public class FoodGroupController {
    @Autowired
    private IFoodGroupService service;

    @GetMapping("/search")
    public List<FoodGroup> queryAll(){
        return service.queryAll();
    }

    @GetMapping("/details/{id}")
    public FoodGroup getFoodGroupById(@PathVariable("id") Long id){
        return service.getOneById(id);
    }

    @PostMapping("/create")
    public FoodGroup createFoodGroup(@Validated @RequestBody FoodGroup foodGroup){
        if(foodGroup.getId() == null) return  service.save(foodGroup);
        FoodGroup foodGroup1 = service.getOneById(foodGroup.getId());
        if(foodGroup1 != null) throw new DuplicateIdException("FoodGroup", foodGroup.getId());
        return service.save(foodGroup);
    }

    @PutMapping("/update/{id}")
    public FoodGroup updateFoodGroup(@PathVariable("id") Long id,
                           @Validated @RequestBody FoodGroup foodGroupRequest) {
        FoodGroup foodGroup = service.getOneById(id);
        foodGroup.setGroup_name(foodGroupRequest.getGroup_name());

        return service.save(foodGroup);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFoodGroup(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
