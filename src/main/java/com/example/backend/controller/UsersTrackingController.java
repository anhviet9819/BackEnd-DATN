package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.model.BloodPressure;
import com.example.backend.model.DiabatesMelitiyus;
import com.example.backend.model.PersonalIndex;
import com.example.backend.model.UsersTracking;
import com.example.backend.repository.BloodPressureRepository;
import com.example.backend.repository.DiabatesMelitiyusRepository;
import com.example.backend.repository.PersonalIndexRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IUsersTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/userstracking")
public class UsersTrackingController {
    @Autowired
    public IUsersTrackingService usersTrackingService;

    @Autowired
    public PersonalIndexRepository personalIndexRepository;

    @Autowired
    public BloodPressureRepository bloodPressureRepository;

    @Autowired
    public DiabatesMelitiyusRepository diabatesMelitiyusRepository;

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @GetMapping("/search")
    public List<UsersTracking> queryAllUsersTracking(){
        return usersTrackingService.queryAll();
    }

    @GetMapping("/details/{id}")
    public UsersTracking findById(@PathVariable("id")Long id){
        return usersTrackingService.findById(id);
    }

    @PostMapping("/create")
    public UsersTracking createUsersTracking(@RequestBody UsersTracking usersTracking) throws ParseException {
        UsersTracking usersTracking1 = usersTrackingService.findById(usersTracking.getId());
        if(usersTracking1 != null) throw new DuplicateIdException("UsersTracking", usersTracking.getId());

        return usersTrackingService.save(usersTracking);
    }

    @PutMapping("update/{id}")
    public UsersTracking updateUsersTrackingByUsersTrackingId(@PathVariable("id") Long id,
                                                              @RequestBody UsersTracking usersTrackingRequest){
        UsersTracking usersTracking = usersTrackingService.findById(id);

        if(usersTrackingRequest.getIs_diabates_meltiyus() != null){
            usersTracking.setIs_diabates_meltiyus(usersTrackingRequest.getIs_diabates_meltiyus());
        }
        if(usersTrackingRequest.getIs_blood_pressure_diseases() != null){
            usersTracking.setIs_blood_pressure_diseases(usersTrackingRequest.getIs_blood_pressure_diseases());
        }
        if(usersTrackingRequest.getIs_heart_diseases() != null){
            usersTracking.setIs_heart_diseases(usersTrackingRequest.getIs_heart_diseases());
        }
        if(usersTrackingRequest.getCurrent_height() != null){
            usersTracking.setCurrent_height(usersTrackingRequest.getCurrent_height());
        }
        if(usersTrackingRequest.getCurrent_weight() != null){
            usersTracking.setCurrent_weight(usersTrackingRequest.getCurrent_weight());
        }
        if(usersTrackingRequest.getCurrent_diastolic() != null){
            usersTracking.setCurrent_diastolic(usersTrackingRequest.getCurrent_diastolic());
        }
        if(usersTrackingRequest.getCurrent_systolic() != null){
            usersTracking.setCurrent_systolic(usersTrackingRequest.getCurrent_systolic());
        }
        if(usersTrackingRequest.getCurrent_blood_before_meal() != null){
            usersTracking.setCurrent_blood_before_meal(usersTrackingRequest.getCurrent_blood_before_meal());
        }
        if(usersTrackingRequest.getCurrent_normal_blood() != null){
            usersTracking.setCurrent_normal_blood(usersTrackingRequest.getCurrent_normal_blood());
        }


        PersonalIndex personalIndex = new PersonalIndex(usersTracking.getCurrent_height(), usersTracking.getCurrent_weight() , usersTracking , Instant.now());
        personalIndexRepository.save(personalIndex);

        BloodPressure bloodPressure = new BloodPressure(usersTracking.getCurrent_diastolic(), usersTracking.getCurrent_systolic(), Instant.now(), usersTracking);
        bloodPressureRepository.save(bloodPressure);

        DiabatesMelitiyus diabatesMelitiyus = new DiabatesMelitiyus(usersTracking.getCurrent_blood_before_meal(), usersTracking.getCurrent_normal_blood(), Instant.now(), usersTracking);
        diabatesMelitiyusRepository.save(diabatesMelitiyus);

        return usersTrackingService.save(usersTracking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsersTracking(@PathVariable("id") Long id){
        usersTrackingService.delete(id);
        return ResponseEntity.ok().build();
    }
}
