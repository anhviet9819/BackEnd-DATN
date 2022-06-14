package com.example.backend.controller;

import com.example.backend.model.ListActivities;
import com.example.backend.repository.ListActivitiesRepository;
import com.example.backend.service.IListActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listactivities")
public class ListActivitiesController {
    @Autowired
    public IListActivitiesService service;

    @Autowired
    ListActivitiesRepository listActivitiesRepository;

//    @GetMapping("/search")
//    public List<ListActivities> queryAll(){
//        return service.queryAll();
//    }

    @GetMapping("/search")
    public Page<ListActivities> findAll(@RequestParam(defaultValue = "0")int page,
                                        @RequestParam(defaultValue = "8")int size,
                                        @RequestParam(defaultValue = "") String nameContaining){
        Pageable paging = PageRequest.of(page, size);
        return listActivitiesRepository.findListActivitiesByNameContainingOrderById(paging, nameContaining);
    }

    @GetMapping("/details/{id}")
    public ListActivities getActivityById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @GetMapping("/details/name")
    public ListActivities getActivityByName(@RequestParam(name = "name")String name){
        return service.findByExistName(name);
    }

    @PostMapping("/create")
    public ListActivities createActivity(@RequestBody ListActivities activities){
        return service.save(activities);
    }
    @PutMapping("/update/{id}")
    public ListActivities updateActivity(@PathVariable("id") Long id,
                                         @Validated @RequestBody ListActivities activityRequest){
        ListActivities activity = service.findById(id);
        activity.setName(activityRequest.getName());
        activity.setCalo_per_hour(activityRequest.getCalo_per_hour());
        activity.setCalo_per_seconds(activityRequest.getCalo_per_seconds());

        return service.save(activity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
