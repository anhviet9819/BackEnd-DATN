package com.example.backend.controller;

import com.example.backend.model.ActivitiesTracking;
import com.example.backend.model.ListActivities;
import com.example.backend.repository.ActivitiesTrackingRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IActivitiesTrackingService;
import com.example.backend.service.impl.ListActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ActivitiesTrackingRepository activitiesTrackingRepository;

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @Autowired
    public ListActivitiesService listActivitiesService;

    @GetMapping("/search")
    public List<ActivitiesTracking> findAll(){
        return activitiesTrackingRepository.findActivitiesTrackingByOrderByCreatedAtDesc();
    }

    @GetMapping("/details/{id}")
    public ActivitiesTracking findById(@PathVariable("id")Long id){
        return activitiesTrackingService.findById(id);
    }

    @GetMapping("/details/userstracking/{userstrackingid}/activitiestracking")
    public List<ActivitiesTracking> findByUserstrackingid(@PathVariable("userstrackingid")Long userstrackingid){
        return activitiesTrackingService.findByUserstrackingId(userstrackingid);
    }

    @GetMapping("/details/listactivities/{listactivitiesid}")
    public List<ActivitiesTracking> findByListActivitiesId(@PathVariable("listactivitiesid")Long listactivitiesid){
        return activitiesTrackingRepository.findActivitiesTrackingByListActivitiesId(listactivitiesid);
    }

    @GetMapping("/details/userstrackingListActivitiesCreatedAt")
    public List<ActivitiesTracking> findByuserstrackingIdListActivitiesIdCreatedAtBetween(@RequestParam(required = false) Long usertrackingid,
                                                                                          @RequestParam(required = false) Instant createdAtStart,
                                                                                          @RequestParam(required = false) Instant createdAtEnd,
                                                                                          @RequestParam(required = false) Long listactivitiesid){
        if(createdAtStart == null && createdAtEnd == null && listactivitiesid == null){
            return activitiesTrackingRepository.findActivitiesTrackingByUsersTrackingIdOrderByCreatedAtDesc(usertrackingid);
        }
        if(createdAtStart == null && createdAtEnd == null){
            return activitiesTrackingRepository.findActivitiesTrackingByUsersTrackingIdAndListActivitiesIdOrderByCreatedAtDesc(usertrackingid, listactivitiesid);
        }
        if(listactivitiesid == null){
            return activitiesTrackingRepository.findActivitiesTrackingByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd);
        }
        return activitiesTrackingRepository.findActivitiesTrackingByUsersTrackingIdAndCreatedAtBetweenAndListActivitiesIdOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd, listactivitiesid);
    }

    @GetMapping("/details/userstrackingListActivitiesCreatedAtPagination")
    public Page<ActivitiesTracking> findByuserstrackingIdListActivitiesIdCreatedAtBetweenPagination(@RequestParam(required = false) Long usertrackingid,
                                                                                                    @RequestParam(required = false) Instant createdAtStart,
                                                                                                    @RequestParam(required = false) Instant createdAtEnd,
                                                                                                    @RequestParam(required = false) Long listactivitiesid,
                                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                                    @RequestParam(defaultValue = "8")int size){
//        System.out.println(usertrackingid);
        Pageable paging = PageRequest.of(page, size);
        if(createdAtStart == null && createdAtEnd == null && listactivitiesid == null){
            return activitiesTrackingRepository.findByUsersTrackingIdOrderByCreatedAtDesc(usertrackingid, paging);
        }
        if(createdAtStart == null && createdAtEnd == null){
            return activitiesTrackingRepository.findByUsersTrackingIdAndListActivitiesIdOrderByCreatedAtDesc(usertrackingid, listactivitiesid, paging);
        }
        if(listactivitiesid == null){
            return activitiesTrackingRepository.findByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd, paging);
        }
        return activitiesTrackingRepository.findByUsersTrackingIdAndCreatedAtBetweenAndListActivitiesIdOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd, listactivitiesid, paging);
    }

    @GetMapping("/details/userstrackingStartTime")
    public Boolean findToNotice(@RequestParam(required = false) Long usertrackingid,
                                @RequestParam(required = false) Instant startTimeStart,
                                @RequestParam(required = false) Instant startTimeEnd){
        List<ActivitiesTracking> activitiesTrackingList = activitiesTrackingRepository.findActivitiesTrackingByUsersTrackingIdAndStartTimeBetween(usertrackingid, startTimeStart, startTimeEnd);
//        System.out.println(activitiesTrackingList);
        if(activitiesTrackingList.size() != 0) return true;
        return false;
    }

    @PostMapping("/create/userstracking/{usertrackingid}/activitiestracking")
    public ActivitiesTracking createActivitiesTrackingByUsersTrackingId(@PathVariable("usertrackingid")Long usertrackingid,
                                                                        @Validated @RequestBody ActivitiesTracking activitiesTracking){
        activitiesTracking.setUsersTracking(usersTrackingRepository.findById(usertrackingid).
                orElseThrow(() -> new ResourceAccessException("Not found Userstracking with id = " + usertrackingid)));

//        ListActivities activityAdding = listActivitiesService.findByExistName(activitiesTracking.getListActivities().getName());
//        System.out.println("Ten la:" + activityAdding.getName());

        ListActivities activityAdding = listActivitiesService.findById(activitiesTracking.getListActivities().getId());

        Long epochStartTime = activitiesTracking.getStart_time().getEpochSecond();
        Long epochEndTime = activitiesTracking.getEnd_time().getEpochSecond();
        Long timeActive = epochEndTime - epochStartTime;

        if(activityAdding != null){
            Double realCaloLoss = activityAdding.getCalo_per_hour() * timeActive / 3600;

            ActivitiesTracking activitiesTracking1 = new ActivitiesTracking(realCaloLoss, Instant.now(), activitiesTracking.getStart_time(), activitiesTracking.getEnd_time(), activitiesTracking.getUsersTracking(), activityAdding);
            return activitiesTrackingService.save(activitiesTracking1);
        }
        else {
            ListActivities newActivityAdding = new ListActivities(activitiesTracking.getListActivities().getName(), activitiesTracking.getListActivities().getCalo_per_hour(), false, "U4");
            listActivitiesService.save(newActivityAdding);

            Double realCaloLoss = newActivityAdding.getCalo_per_hour() * timeActive / 3600;
            ActivitiesTracking activitiesTracking1 = new ActivitiesTracking(realCaloLoss, Instant.now(), activitiesTracking.getStart_time(), activitiesTracking.getEnd_time(), activitiesTracking.getUsersTracking(), newActivityAdding);
            return activitiesTrackingService.save(activitiesTracking1);

        }
    }

    @PutMapping("update/{id}")
    public ActivitiesTracking updateActivitiesTrackingById(@PathVariable("id")Long id,
                                                           @Validated @RequestBody ActivitiesTracking activitiesTrackingRequest){
        ActivitiesTracking activitiesTracking = activitiesTrackingService.findById(id);
        System.out.println("Viet");
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
//            ListActivities listActivitiesRequest = listActivitiesService.findByExistName(activitiesTrackingRequest.getListActivities().getName());
            ListActivities listActivitiesRequest = listActivitiesService.findById(activitiesTrackingRequest.getListActivities().getId());
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
        else{
            Long epochStartTime = activitiesTracking.getStart_time().getEpochSecond();
            Long epochEndTime = activitiesTracking.getEnd_time().getEpochSecond();
            Long timeActive = epochEndTime - epochStartTime;

            Double realCaloLoss = activitiesTracking.getListActivities().getCalo_per_hour() * timeActive / 3600;
            activitiesTracking.setCalo_loss(realCaloLoss);
        }


        return activitiesTrackingService.save(activitiesTracking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delelteById(@PathVariable("id") Long id){
        activitiesTrackingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
