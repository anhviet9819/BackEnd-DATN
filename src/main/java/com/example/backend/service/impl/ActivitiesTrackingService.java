package com.example.backend.service.impl;

import com.example.backend.model.ActivitiesTracking;
import com.example.backend.repository.ActivitiesTrackingRepository;
import com.example.backend.service.IActivitiesTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesTrackingService implements IActivitiesTrackingService {
    @Autowired
    public ActivitiesTrackingRepository activitiesTrackingRepository;

    @Override
    public List<ActivitiesTracking> findAll() {
        return activitiesTrackingRepository.findAll();
    }

    @Override
    public ActivitiesTracking findById(Long id) {
        return activitiesTrackingRepository.findById(id).orElse(null);
    }

    @Override
    public List<ActivitiesTracking> findByUserstrackingId(Long userstrackingId) {
        return activitiesTrackingRepository.findActivitiesTrackingByUsersTrackingId(userstrackingId);
    }

    @Override
    public ActivitiesTracking save(ActivitiesTracking activitiesTracking) {
        return activitiesTrackingRepository.save(activitiesTracking);
    }

    @Override
    public void deleteById(Long id) {
        activitiesTrackingRepository.deleteById(id);
    }
}
