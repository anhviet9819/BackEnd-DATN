package com.example.backend.service.impl;

import com.example.backend.model.MealsTracking;
import com.example.backend.repository.MealsTrackingRepository;
import com.example.backend.service.IMealsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealsTrackingService implements IMealsTrackingService {

    @Autowired
    public MealsTrackingRepository mealsTrackingRepository;

    @Override
    public List<MealsTracking> findAll() {
        return mealsTrackingRepository.findAll();
    }

//    @Override
//    public List<MealsTracking> findByMealFoodId(Long foodId) {
//        return mealsTrackingRepository.findMealsTrackingByFoodsId(foodId);
//        return mealsTrackingRepository
//    }

    @Override
    public MealsTracking findById(Long id) {
        return mealsTrackingRepository.findById(id).orElse(null);
    }

    @Override
    public List<MealsTracking> findByUserstrackingId(Long userstrackingId) {
        return mealsTrackingRepository.findMealsTrackingByUsersTrackingId(userstrackingId);
    }

    @Override
    public MealsTracking save(MealsTracking mealsTracking) {
        return mealsTrackingRepository.save(mealsTracking);
    }

    @Override
    public void delete(Long id) {
        mealsTrackingRepository.deleteById(id);
    }
}
