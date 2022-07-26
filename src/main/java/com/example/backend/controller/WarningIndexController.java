package com.example.backend.controller;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.User;
import com.example.backend.model.WarningIndex;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.repository.WarningIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("/api/warningindex")
public class WarningIndexController {
    @Autowired
    WarningIndexRepository warningIndexRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/details/usertracking/{usertrackingid}")
    public WarningIndex findByUserTrackingId(@PathVariable("usertrackingid")Long usertrackingid){
//        User user = userRepository.findByUsersTrackingId(usertrackingid);
//        System.out.println(Period.between(user.getBirthday().toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate(), Date.from(Instant.now()).toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate()).getYears());
        WarningIndex warningIndex = warningIndexRepository.findByUsersTrackingId(usertrackingid);
        return warningIndex;
    }

    @PutMapping("/update/usertracking/{usertrackingid}")
    public WarningIndex updateByUserTrackingId(@PathVariable("usertrackingid")Long usertrackingid,
                                       @Validated @RequestBody WarningIndex warningIndexRequest){
        WarningIndex warningIndex = warningIndexRepository.findByUsersTrackingId(usertrackingid);
        if(warningIndex == null) throw new ResourceNotFoundException("Not found warningIndex with usertrackingid: "+ usertrackingid);
        if(warningIndexRequest.getHighDiastolic() != null){
            warningIndex.setHighDiastolic(warningIndexRequest.getHighDiastolic());
        }
        if(warningIndexRequest.getLowDiastolic() != null){
            warningIndex.setLowDiastolic(warningIndexRequest.getLowDiastolic());
        }
        if(warningIndexRequest.getHighSystolic() != null){
            warningIndex.setHighSystolic(warningIndexRequest.getHighSystolic());
        }
        if(warningIndexRequest.getLowSystolic() != null){
            warningIndex.setLowSystolic(warningIndexRequest.getLowSystolic());
        }
        if(warningIndexRequest.getHighWeight() != null){
            warningIndex.setHighWeight(warningIndexRequest.getHighWeight());
        }
        if(warningIndexRequest.getLowWeight() != null){
            warningIndex.setLowWeight(warningIndexRequest.getLowWeight());
        }
        if(warningIndexRequest.getHighGlycemic() != null){
            warningIndex.setHighGlycemic(warningIndexRequest.getHighGlycemic());
        }
        if(warningIndexRequest.getLowGlycemic() != null){
            warningIndex.setLowGlycemic(warningIndexRequest.getLowGlycemic());
        }
        return warningIndex;
    }
}
