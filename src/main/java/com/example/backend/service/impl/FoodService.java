package com.example.backend.service.impl;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Food;
import com.example.backend.repository.FoodRepository;
import com.example.backend.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService {

    @Autowired
    public FoodRepository foodRepository;

//    @Override
//    public List<Food> queryByFoodGroup(Long FoodGroupId) throws ResourceNotFoundException {
//        if(FoodGroupId == null) return foodRepository.findAll();
//        List<Food> food = foodRepository.findFoodByFoodGroupId(FoodGroupId);
//        if(food.size() == 0) throw  new ResourceNotFoundException("Food", "FoodGroup", FoodGroupId);
//        return food;
//    }

    @Override
    public List<Food> queryAll() {
        return foodRepository.findAll();
    }

    @Override
    public List<Food> queryByFoodGroup(Long FoodGroupId) throws ResourceNotFoundException {
        if(FoodGroupId == null) return foodRepository.findAll();
        List<Food> food = foodRepository.findFoodByFoodGroupId(FoodGroupId);
        if(food.size() == 0) throw  new ResourceNotFoundException("Food", "FoodGroup", FoodGroupId);
        return food;
    }

//    @Override
//    public List<Food> queryByMealsTrackingId(Long mealsTrackingId) {
//        return foodRepository.findFoodByMealsTrackingId(mealsTrackingId);
//    }

    @Override
    public Food findById(Long id) {
        Food food = foodRepository.findById(id).orElse(null);
        return food;
    }

    @Override
    public Food findByExistName(String foodName) {
        return foodRepository.findFoodByNameContaining(foodName);
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public Long generateFoodId(Long foodGroupId) {
        List<Food> foodList = foodRepository.findFoodByFoodGroupIdOrderById(foodGroupId);
        if(foodList.size() == 0) {
            return 1500000001L;
        }
        Food foodMaxId = foodList.get(foodList.size() - 1);
        Long foodIdGenerated = foodMaxId.getId() + 1;
        return foodIdGenerated;
    }
}
