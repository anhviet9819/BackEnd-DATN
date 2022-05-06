package com.example.backend.service.impl;

import com.example.backend.model.PersonalIndex;
import com.example.backend.model.UsersTracking;
import com.example.backend.repository.PersonalIndexRepository;
import com.example.backend.service.IPersonalIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalIndexService implements IPersonalIndexService {

    @Autowired
    public PersonalIndexRepository personalIndexRepository;

    @Override
    public PersonalIndex findById(Long id) {
        return personalIndexRepository.findById(id).orElse(null);
    }

    @Override
    public List<PersonalIndex> findByUserTrackingId(UsersTracking usersTracking) {
        return personalIndexRepository.findAllByUsersTrackingId(usersTracking.getId());
    }

    @Override
    public PersonalIndex save(PersonalIndex personalIndex) {
        return personalIndexRepository.save(personalIndex);
    }

    @Override
    public void delete(Long id) {
        personalIndexRepository.deleteById(id);
    }

}
