package com.example.backend.service;

import com.example.backend.model.BloodPressure;

public interface IBloodPressureService {
    BloodPressure findById(Long id);
    BloodPressure save(BloodPressure bloodPressure);
    void delete(Long id);
}
