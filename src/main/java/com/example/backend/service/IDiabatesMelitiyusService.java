package com.example.backend.service;

import com.example.backend.model.BloodPressure;
import com.example.backend.model.DiabatesMelitiyus;

public interface IDiabatesMelitiyusService {
    DiabatesMelitiyus findById(Long id);
    DiabatesMelitiyus save(DiabatesMelitiyus diabatesMelitiyus);
    void delete(Long id);
}
