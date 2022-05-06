package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.BloodPressure;
import com.example.backend.model.PersonalIndex;
import com.example.backend.repository.BloodPressureRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IBloodPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodpressure")
public class BloodPressureController {
    @Autowired
    public IBloodPressureService bloodPressureService;

    @Autowired
    public BloodPressureRepository bloodPressureRepository;

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @GetMapping("/search")
    public List<BloodPressure> getAll(){
        return bloodPressureRepository.findAll();
    }

    @GetMapping("/details/{id}")
    public BloodPressure findById(@PathVariable("id") Long id){
        return bloodPressureService.findById(id);
    }

    @GetMapping("/userstracking/{userstrackingid}/bloodpressure")
    public List<BloodPressure> findByUsersTrackingId(@PathVariable("userstrackingid") Long userstrackingid){
        return bloodPressureRepository.findAllByUsersTrackingId(userstrackingid);
    }

    @PostMapping("/userstracking/{userstrackingid}/bloodpressure")
    public BloodPressure createPersonalIndexByUsersTrackingId(@PathVariable("userstrackingid")Long userstrackingid,
                                                              @RequestBody BloodPressure bloodPressure){
        BloodPressure bloodPressure1 = bloodPressureService.findById(bloodPressure.getId());
        if(bloodPressure1 != null) throw new DuplicateIdException("bloodPressure", bloodPressure.getId());

        return bloodPressureService.save(bloodPressure);
    }

    @PutMapping("update/{id}")
    public BloodPressure updateBloodPressure(@PathVariable("id")Long id,
                                             @RequestBody BloodPressure bloodPressureRequest){
        BloodPressure bloodPressure = bloodPressureService.findById(id);
        if(bloodPressureRequest.getDiastolic() != null){
            bloodPressure.setDiastolic(bloodPressureRequest.getDiastolic());
        }
        if(bloodPressureRequest.getSystolic() != null){
            bloodPressure.setSystolic(bloodPressureRequest.getSystolic());
        }
        return bloodPressureService.save(bloodPressure);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteBloodPressure(@PathVariable("id")Long id){
        bloodPressureService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/UsersTracking/{userstrackingid}/bloodpressure")
    public ResponseEntity<?> deleteBloodPressureByUsersTrackingId(@PathVariable("userstrackingid")Long userstrackingid){
        if(!usersTrackingRepository.existsById(userstrackingid)) {
            throw new ResourceNotFoundException("Not found userstracking with id = " + userstrackingid);
        }
        bloodPressureRepository.deleteBloodPressureByUsersTrackingId(userstrackingid);
        return ResponseEntity.ok().build();
    }
}
