package com.example.backend.service.impl;

import com.example.backend.model.BloodPressure;
import com.example.backend.repository.BloodPressureRepository;
import com.example.backend.service.IBloodPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodPressureService implements IBloodPressureService{

    @Autowired
    public BloodPressureRepository bloodPressureRepository;

    @Override
    public BloodPressure findById(Long id) {
        return bloodPressureRepository.findById(id).orElse(null);
    }

    @Override
    public BloodPressure save(BloodPressure bloodPressure) {
        return bloodPressureRepository.save(bloodPressure);
    }

    @Override
    public void delete(Long id) {
        bloodPressureRepository.deleteById(id);
    }
}
