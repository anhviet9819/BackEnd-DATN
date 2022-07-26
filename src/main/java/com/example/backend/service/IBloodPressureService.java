package com.example.backend.service;

import com.example.backend.model.BloodPressure;

import java.util.List;

public interface IBloodPressureService {
    BloodPressure findById(Long id);
    BloodPressure save(BloodPressure bloodPressure);
    void delete(Long id);
//    List<BloodPressure> findAllByCreated_atAsc(Long usertrackingid);
}
