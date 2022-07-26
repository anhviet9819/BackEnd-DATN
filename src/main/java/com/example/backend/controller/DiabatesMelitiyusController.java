package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.BloodPressure;
import com.example.backend.model.DiabatesMelitiyus;
import com.example.backend.model.PersonalIndex;
import com.example.backend.repository.DiabatesMelitiyusRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IDiabatesMelitiyusService;
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

    @GetMapping("/userstracking/{userstrackingid}/pagination")
    public Page<DiabatesMelitiyus> findByUsersTrackingIdPagination(@PathVariable("userstrackingid") Long userstrackingid,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "8")int size,
                                                               @RequestParam()String sorting){
        Pageable paging = PageRequest.of(page, size);
        if(sorting.equals("desc") == true){
            return diabatesMelitiyusRepository.findAllByUsersTrackingIdOrderByCreatedAtDescPagination(userstrackingid, paging);
        }
        else return diabatesMelitiyusRepository.findAllByUsersTrackingIdOrderByCreatedAtAscPagination(userstrackingid, paging);
    }

    @GetMapping("/bieudo/{usertrackingid}")
    public List<DiabatesMelitiyus> bieudoThongke(@PathVariable("usertrackingid") Long usertrackingid,
                                             @RequestParam() String starttime,
                                             @RequestParam() String endtime) throws ParseException {
        if(starttime == "" && endtime == ""){
            return diabatesMelitiyusRepository.findAllByUsersTrackingIdOrderByCreatedAtAsc(usertrackingid);
        }
        Date start1 = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
        Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
        return diabatesMelitiyusRepository.bieudoThongke(usertrackingid, start1, end1);
    }

//    @GetMapping("/bieudo/{usertrackingid}")
//    public Page<DiabatesMelitiyus> bieudoThongkePagination(@PathVariable("usertrackingid") Long usertrackingid,
//                                                           @RequestParam() String starttime,
//                                                           @RequestParam() String endtime,
//                                                           @RequestParam(defaultValue = "0")int page,
//                                                           @RequestParam(defaultValue = "8")int size) throws ParseException {
//        Pageable paging = PageRequest.of(page, size);
//        if(starttime == "" && endtime == ""){
//            return diabatesMelitiyusRepository.findAllByUsersTrackingIdOrderByCreatedAtAsc(usertrackingid);
//        }
//        Date start1 = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
//        Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
//        return diabatesMelitiyusRepository.bieudoThongke(usertrackingid, start1, end1);
//    }

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
