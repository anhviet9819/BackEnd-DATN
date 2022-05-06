package com.example.backend.service;

import com.example.backend.model.PersonalIndex;
import com.example.backend.model.UsersTracking;

import java.util.List;
import java.util.Optional;

public interface IPersonalIndexService {
    PersonalIndex findById(Long id);
    List<PersonalIndex> findByUserTrackingId(UsersTracking usersTracking);
    PersonalIndex save(PersonalIndex personalIndex);
    void delete(Long id);
}
