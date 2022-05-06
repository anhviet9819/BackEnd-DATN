package com.example.backend.repository;

import com.example.backend.model.BloodPressure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressure,Long> {
    List<BloodPressure> findAllByUsersTrackingId(Long id);
    void deleteBloodPressureByUsersTrackingId(Long id);
}
