package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.PersonalIndex;
import com.example.backend.repository.PersonalIndexRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IPersonalIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personalindex")
public class PersonalIndexController {
    @Autowired
    public IPersonalIndexService personalIndexService;

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @Autowired
    public PersonalIndexRepository personalIndexRepository;

    @GetMapping("/search")
    public List<PersonalIndex> getAll(){
        return personalIndexRepository.findAll();
    }

    @GetMapping("/details/{id}")
    public PersonalIndex findById(@PathVariable("id") Long id){
        return personalIndexService.findById(id);
    }

    @GetMapping("/userstracking/{userstrackingid}/personalindex")
    public List<PersonalIndex> findByUsersTrackingId(@PathVariable("userstrackingid") Long userstrackingid){
        return personalIndexRepository.findAllByUsersTrackingId(userstrackingid);
    }

    @PostMapping("/userstracking/{userstrackingid}/personalindex")
    public PersonalIndex createPersonalIndexByUsersTrackingId(@PathVariable("userstrackingid")Long userstrackingid,
                                                              @RequestBody PersonalIndex personalIndex){
        PersonalIndex personalIndex1 = personalIndexService.findById(personalIndex.getId());
        if(personalIndex1 != null) throw new DuplicateIdException("PersonalIndex", personalIndex.getId());

        return personalIndexService.save(personalIndex);
    }

    @PutMapping("update/{id}")
    public PersonalIndex updatePersonalIndex(@PathVariable("id")Long id,
                                             @RequestBody PersonalIndex personalIndexRequest){
        PersonalIndex personalIndex = personalIndexService.findById(id);
        if(personalIndexRequest.getHeight() != null){
            personalIndex.setHeight(personalIndexRequest.getHeight());
        }
        if(personalIndexRequest.getWeight() != null){
            personalIndex.setWeight(personalIndexRequest.getWeight());
        }
        return personalIndexService.save(personalIndex);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePersonalIndex(@PathVariable("id")Long id){
        personalIndexService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/usersTracking/{usertrackingid}/personalindex")
    public ResponseEntity<?> deleteAllPersonalIndexByUsersTrackingId(@PathVariable("usertrackingid")Long usertrackingid){
        if(!usersTrackingRepository.existsById(usertrackingid)) {
            throw new ResourceNotFoundException("Not found userstracking with id = " + usertrackingid);
        }
        personalIndexRepository.deletePersonalIndexByUsersTrackingId(usertrackingid);
        return ResponseEntity.ok().build();
    }
}
