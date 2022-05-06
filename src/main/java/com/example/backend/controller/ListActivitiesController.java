package com.example.backend.controller;

import com.example.backend.model.ListActivities;
import com.example.backend.service.IListActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listactivities")
public class ListActivitiesController {
    @Autowired
    public IListActivitiesService service;

    @GetMapping("/search")
    public List<ListActivities> queryAll(){
        return service.queryAll();
    }

    @GetMapping("/details/{id}")
    public ListActivities getActivityById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PutMapping("/update/{id}")
    public ListActivities updateActivity(@PathVariable("id") Long id,
                                         @Validated @RequestBody ListActivities activityRequest){
        ListActivities activity = service.findById(id);
        activity.setName(activityRequest.getName());
        activity.setCalo_per_seconds(activityRequest.getCalo_per_seconds());

        return service.save(activity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
