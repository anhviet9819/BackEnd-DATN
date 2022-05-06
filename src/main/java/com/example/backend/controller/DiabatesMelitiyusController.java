package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.DiabatesMelitiyus;
import com.example.backend.repository.DiabatesMelitiyusRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IDiabatesMelitiyusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diabatesmelitiyus")
public class DiabatesMelitiyusController {
    @Autowired
    public IDiabatesMelitiyusService diabatesMelitiyusService;

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @Autowired
    public DiabatesMelitiyusRepository diabatesMelitiyusRepository;

    @GetMapping("/search")
    public List<DiabatesMelitiyus> getAll(){
        return diabatesMelitiyusRepository.findAll();
    }

    @GetMapping("/details/{id}")
    public DiabatesMelitiyus findById(@PathVariable("id") Long id){
        return diabatesMelitiyusService.findById(id);
    }

    @GetMapping("/userstracking/{userstrackingid}/diabatesmelitiyus")
    public List<DiabatesMelitiyus> findByUsersTrackingId(@PathVariable("userstrackingid") Long userstrackingid){
        return diabatesMelitiyusRepository.findAllByUsersTrackingId(userstrackingid);
    }

    @PostMapping("/userstracking/{userstrackingid}/diabatesmelitiyus")
    public DiabatesMelitiyus createDiabatesMelitiyusByUsersTrackingId(@PathVariable("userstrackingid")Long userstrackingid,
                                                              @RequestBody DiabatesMelitiyus diabatesMelitiyus){
        DiabatesMelitiyus diabatesMelitiyus1 = diabatesMelitiyusService.findById(diabatesMelitiyus.getId());
        if(diabatesMelitiyus1 != null) throw new DuplicateIdException("DiabatesMelitiyus", diabatesMelitiyus.getId());

        return diabatesMelitiyusService.save(diabatesMelitiyus);
    }

    @PutMapping("update/{id}")
    public DiabatesMelitiyus updateDiabatesMelitiyus(@PathVariable("id")Long id,
                                             @RequestBody DiabatesMelitiyus diabatesMelitiyusRequest){
        DiabatesMelitiyus diabatesMelitiyus = diabatesMelitiyusService.findById(id);
        if(diabatesMelitiyusRequest.getBlood_glucose_after_meal() != null){
            diabatesMelitiyus.setBlood_glucose_after_meal(diabatesMelitiyusRequest.getBlood_glucose_after_meal());
        }
        if(diabatesMelitiyusRequest.getBlood_glucose_before_meal() != null){
            diabatesMelitiyus.setBlood_glucose_before_meal(diabatesMelitiyusRequest.getBlood_glucose_before_meal());
        }
        return diabatesMelitiyusService.save(diabatesMelitiyus);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDiabatesMelitiyus(@PathVariable("id")Long id){
        diabatesMelitiyusService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/usersTracking/{usertrackingid}/personalindex")
    public ResponseEntity<?> deleteAllDiabatesMelitiyusByUsersTrackingId(@PathVariable("usertrackingid")Long usertrackingid){
        if(!usersTrackingRepository.existsById(usertrackingid)) {
            throw new ResourceNotFoundException("Not found userstracking with id = " + usertrackingid);
        }
        diabatesMelitiyusRepository.deleteDiabatesMelitiyusByUsersTrackingId(usertrackingid);
        return ResponseEntity.ok().build();
    }
}
