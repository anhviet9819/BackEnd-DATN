package com.example.backend.service.impl;

import com.example.backend.model.UsersTracking;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IUsersTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTrackingService implements IUsersTrackingService {

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @Override
    public List<UsersTracking> queryAll() {
        return usersTrackingRepository.findAll();
    }

    @Override
    public UsersTracking findById(Long id) {
        return usersTrackingRepository.findById(id).orElse(null);
    }

    @Override
    public UsersTracking save(UsersTracking usersTracking) {
        return usersTrackingRepository.save(usersTracking);
    }

    @Override
    public void delete(Long id) {
        usersTrackingRepository.deleteById(id);
    }
}
