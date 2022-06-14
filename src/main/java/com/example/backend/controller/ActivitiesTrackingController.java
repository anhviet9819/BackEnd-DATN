package com.example.backend.controller;

import com.example.backend.model.ActivitiesTracking;
import com.example.backend.model.ListActivities;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IActivitiesTrackingService;
import com.example.backend.service.impl.ListActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/activitiestracking")
public class ActivitiesTrackingController {
    @Autowired
    public IActivitiesTrackingService activitiesTrackingService;

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @Autowired
    public ListActivitiesService listActivitiesService;

    @GetMapping("/search")
    public List<ActivitiesTracking> findAll(){
        return activitiesTrackingService.findAll();
    }

    @GetMapping("/details/{id}")
    public ActivitiesTracking findById(@PathVariable("id")Long id){
        return activitiesTrackingService.findById(id);
    }

    @GetMapping("/details/userstracking/{userstrackingid}/activitiestracking")
    public List<ActivitiesTracking> findByUserstrackingid(@PathVariable("userstrackingid")Long userstrackingid){
        return activitiesTrackingService.findByUserstrackingId(userstrackingid);
    }

    @PostMapping("/create/userstracking/{usertrackingid}/activitiestracking")
    public ActivitiesTracking createActivitiesTrackingByUsersTrackingId(@PathVariable("usertrackingid")Long usertrackingid,
                                                                        @Validated @RequestBody ActivitiesTracking activitiesTracking){
        activitiesTracking.setUsersTracking(usersTrackingRepository.findById(usertrackingid).
                orElseThrow(() -> new ResourceAccessException("Not found Userstracking with id = " + usertrackingid)));

        ListActivities activityAdding = listActivitiesService.findByExistName(activitiesTracking.getListActivities().getName());
//        System.out.println("Ten la:" + activityAdding.getName());
        Long epochStartTime = activitiesTracking.getStart_time().getEpochSecond();
        Long epochEndTime = activitiesTracking.getEnd_time().getEpochSecond();
        Long timeActive = epochEndTime - epochStartTime;

//        System.out.println(activitiesTracking.getStart_time());
//        System.out.println(activitiesTracking.getEnd_time());

        Double realCaloLoss = activityAdding.getCalo_per_hour() * timeActive / 3600;

        ActivitiesTracking activitiesTracking1 = new ActivitiesTracking(realCaloLoss, Instant.now(), activitiesTracking.getStart_time(), activitiesTracking.getEnd_time(), activitiesTracking.getUsersTracking(), activityAdding);
        return activitiesTrackingService.save(activitiesTracking1);
    }

    @PutMapping("update/{id}")
    public ActivitiesTracking updateActivitiesTrackingById(@PathVariable("id")Long id,
                                                           @Validated @RequestBody ActivitiesTracking activitiesTrackingRequest){
        ActivitiesTracking activitiesTracking = activitiesTrackingService.findById(id);

        if(activitiesTrackingRequest.getCreated_at() != null){
            activitiesTracking.setCreated_at(activitiesTrackingRequest.getCreated_at());
        }
        if(activitiesTrackingRequest.getStart_time() != null){
            activitiesTracking.setStart_time(activitiesTrackingRequest.getStart_time());
        }
        if(activitiesTrackingRequest.getEnd_time() != null){
            activitiesTracking.setEnd_time(activitiesTrackingRequest.getEnd_time());
        }
        if(activitiesTrackingRequest.getListActivities() != null){
            ListActivities listActivitiesRequest = listActivitiesService.findByExistName(activitiesTrackingRequest.getListActivities().getName());
            activitiesTracking.setListActivities(listActivitiesRequest);
            // Doan nay can xem lai
            // Neu nguoi dung sua lai type_activity
            Long epochStartTime = activitiesTracking.getStart_time().getEpochSecond();
            Long epochEndTime = activitiesTracking.getEnd_time().getEpochSecond();
            Long timeActive = epochEndTime - epochStartTime;

            Double realCaloLoss = listActivitiesRequest.getCalo_per_hour() * timeActive / 3600;
            activitiesTracking.setCalo_loss(realCaloLoss);
        }
        //Khong sua type_activity
        Long epochStartTime = activitiesTracking.getStart_time().getEpochSecond();
        Long epochEndTime = activitiesTracking.getEnd_time().getEpochSecond();
        Long timeActive = epochEndTime - epochStartTime;

        Double realCaloLoss = activitiesTracking.getListActivities().getCalo_per_hour() * timeActive / 3600;
        activitiesTracking.setCalo_loss(realCaloLoss);

        return activitiesTrackingService.save(activitiesTracking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delelteById(@PathVariable("id") Long id){
        activitiesTrackingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
