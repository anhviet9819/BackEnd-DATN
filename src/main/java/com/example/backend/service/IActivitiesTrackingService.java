package com.example.backend.service;

import com.example.backend.model.ActivitiesTracking;

import java.util.List;

public interface IActivitiesTrackingService {
    List<ActivitiesTracking> findAll();

    ActivitiesTracking findById(Long id);

    List<ActivitiesTracking> findByUserstrackingId(Long userstrackingId);

    ActivitiesTracking save(ActivitiesTracking activitiesTracking);

    void deleteById(Long id);
}
