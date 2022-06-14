package com.example.backend.service;

import com.example.backend.model.ListActivities;

import java.util.List;

public interface IListActivitiesService {
    List<ListActivities> queryAll();
    ListActivities findById(Long id);

    ListActivities findByExistName(String name);
    ListActivities save(ListActivities list_activities);
    void delete(Long id);
}
