package com.example.backend.service.impl;

import com.example.backend.model.ListActivities;
import com.example.backend.repository.ListActivitiesRepository;
import com.example.backend.service.IListActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListActivitiesService implements IListActivitiesService {

    @Autowired
    public ListActivitiesRepository list_activitiesRepository;


    @Override
    public List<ListActivities> queryAll() {
        return list_activitiesRepository.findAll();
    }

    @Override
    public ListActivities findById(Long id) {
        return list_activitiesRepository.findById(id).orElse(null);
    }

    @Override
    public ListActivities save(ListActivities list_activities) {
        return list_activitiesRepository.save(list_activities);
    }

    @Override
    public void delete(Long id) {
        list_activitiesRepository.deleteById(id);
    }
}
