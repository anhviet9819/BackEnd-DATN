package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.PersonalIndex;
import com.example.backend.repository.PersonalIndexRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IPersonalIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/userstracking/{userstrackingid}/pagination")
    public Page<PersonalIndex> findByUsersTrackingIdPagination(@PathVariable("userstrackingid") Long userstrackingid,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "8")int size,
                                                               @RequestParam()String sorting){
        Pageable paging = PageRequest.of(page, size);
        if(sorting.equals("desc") == true){
            return personalIndexRepository.findAllByUsersTrackingIdOrderByCreatedAtDescPagination(userstrackingid, paging);
        }
        else return personalIndexRepository.findAllByUsersTrackingIdOrderByCreatedAtAscPagination(userstrackingid, paging);
    }

    @GetMapping("/userstracking/{userstrackingid}")
    public List<PersonalIndex> findByUsersTrackingIdFilters(@PathVariable("userstrackingid") Long userstrackingid,
                                                            @RequestParam()String sorting){
//        System.out.println(sorting);
        if(sorting.equals("desc") == true){
            return personalIndexRepository.findAllByUsersTrackingIdOrderByCreatedAtDesc(userstrackingid);
        }
        else return personalIndexRepository.findAllByUsersTrackingIdOrderByCreatedAtAsc(userstrackingid);
    }

    @GetMapping("/bieudo/{usertrackingid}")
    public List<PersonalIndex> bieudoThongke(@PathVariable("usertrackingid") Long usertrackingid,
                                             @RequestParam() String starttime,
                                             @RequestParam() String endtime) throws ParseException {
        if(starttime == "" && endtime == ""){
         return personalIndexRepository.findAllByUsersTrackingIdOrderByCreatedAtAsc(usertrackingid);
        }
        Date start1 = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
        Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
        return personalIndexRepository.bieudoThongke(usertrackingid, start1, end1);
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
