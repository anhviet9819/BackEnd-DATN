package com.example.backend.service;

import com.example.backend.model.UsersTracking;

import java.util.List;

public interface IUsersTrackingService {
    List<UsersTracking> queryAll();
    UsersTracking findById(Long id);
    UsersTracking save(UsersTracking usersTracking);
    void delete(Long id);
}
