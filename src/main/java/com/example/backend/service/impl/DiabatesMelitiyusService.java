package com.example.backend.service.impl;

import com.example.backend.model.BloodPressure;
import com.example.backend.model.DiabatesMelitiyus;
import com.example.backend.repository.DiabatesMelitiyusRepository;
import com.example.backend.service.IDiabatesMelitiyusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiabatesMelitiyusService implements IDiabatesMelitiyusService {
    @Autowired
    public DiabatesMelitiyusRepository diabatesMelitiyusRepository;

    @Override
    public DiabatesMelitiyus findById(Long id) {
        return diabatesMelitiyusRepository.findById(id).orElse(null);
    }

    @Override
    public DiabatesMelitiyus save(DiabatesMelitiyus diabatesMelitiyus) {
        return diabatesMelitiyusRepository.save(diabatesMelitiyus);
    }

    @Override
    public void delete(Long id) {
        diabatesMelitiyusRepository.deleteById(id);
    }
}
