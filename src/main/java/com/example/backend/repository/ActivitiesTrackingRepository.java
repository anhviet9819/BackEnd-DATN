package com.example.backend.repository;

import com.example.backend.model.ActivitiesTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitiesTrackingRepository extends JpaRepository<ActivitiesTracking, Long> {
    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingId(Long userstrackingId);
}
