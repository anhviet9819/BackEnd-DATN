package com.example.backend.repository;

import com.example.backend.model.MealsTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealsTrackingRepository extends JpaRepository<MealsTracking, Long> {
//    List<MealsTracking> findMealsTrackingByFoodsId(Long foodId);
//    List<MealsTracking> findMealsTrackingByMealFoods
    List<MealsTracking> findMealsTrackingByUsersTrackingId(Long userstrackingid);
}
