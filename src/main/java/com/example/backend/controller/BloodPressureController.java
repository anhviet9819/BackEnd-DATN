package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.BloodPressure;
import com.example.backend.model.DiabatesMelitiyus;
import com.example.backend.model.PersonalIndex;
import com.example.backend.repository.BloodPressureRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IBloodPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

//    @GetMapping("/searchorderby")
//    public List<BloodPressure> getOrderBy(){
//        return bloodPressureRepository.findAllByOrderByCreated_at();
//    }

    @GetMapping("/details/{id}")
    public BloodPressure findById(@PathVariable("id") Long id){
        return bloodPressureService.findById(id);
    }

    @GetMapping("/userstracking/{userstrackingid}/bloodpressure")
    public List<BloodPressure> findByUsersTrackingId(@PathVariable("userstrackingid") Long userstrackingid){
        return bloodPressureRepository.findAllByUsersTrackingId(userstrackingid);
    }

    @GetMapping("/userstracking/{userstrackingid}/pagination")
    public Page<BloodPressure> findByUsersTrackingIdPagination(@PathVariable("userstrackingid") Long userstrackingid,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "8")int size,
                                                               @RequestParam()String sorting){
        Pageable paging = PageRequest.of(page, size);
        if(sorting.equals("desc") == true){
            return bloodPressureRepository.findAllByUsersTrackingIdOrderByCreatedAtDescPagination(userstrackingid, paging);
        }
        else return bloodPressureRepository.findAllByUsersTrackingIdOrderByCreatedAtAscPagination(userstrackingid, paging);
    }

    @GetMapping("/bieudo/{usertrackingid}")
    public List<BloodPressure> bieudoThongke(@PathVariable("usertrackingid") Long usertrackingid,
                                                 @RequestParam() String starttime,
                                                 @RequestParam() String endtime) throws ParseException {
        if(starttime == "" && endtime == ""){
            return bloodPressureRepository.findAllByUsersTrackingIdOrderByCreatedAtAsc(usertrackingid);
        }
        Date start1 = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
        Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
        return bloodPressureRepository.bieudoThongke(usertrackingid, start1, end1);
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
