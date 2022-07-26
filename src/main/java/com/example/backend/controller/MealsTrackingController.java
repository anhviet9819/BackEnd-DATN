package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.model.ActivitiesTracking;
import com.example.backend.model.MealsTracking;
import com.example.backend.model.UsersTracking;
import com.example.backend.repository.FoodRepository;
import com.example.backend.repository.MealsTrackingRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IMealsTrackingService;
import com.example.backend.service.IUsersTrackingService;
import com.example.backend.service.impl.MealsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/api/mealstracking")
public class MealsTrackingController {
    @Autowired
    public IMealsTrackingService mealsTrackingService;

    @Autowired
    public UsersTrackingRepository usersTrackingRepository;

    @Autowired
    public MealsTrackingRepository mealsTrackingRepository;

    @Autowired
    public FoodRepository foodRepository;

    @GetMapping("/search")
    public List<MealsTracking> findAll(){
        return mealsTrackingService.findAll();
    }

    @GetMapping("/details/{id}")
    public MealsTracking findById(@PathVariable("id") Long id){
        return mealsTrackingService.findById(id);
    }

//    @GetMapping("/details/mealstracking/{foodid}/food")
//    public List<MealsTracking> findByFoodId(@PathVariable("foodid") Long foodId){
//        return mealsTrackingService.findByMealFood()
//    }

    @GetMapping("/details/userstrackingTypecreatedAt")
    public List<MealsTracking> findByuserstrackingIdListActivitiesIdCreatedAtBetween(@RequestParam(required = false) Long usertrackingid,
                                                                                          @RequestParam(required = false) Instant createdAtStart,
                                                                                          @RequestParam(required = false) Instant createdAtEnd,
                                                                                          @RequestParam(required = false) String type){
        if(createdAtStart == null && createdAtEnd == null && type == ""){
            return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdOrderByCreatedAtDesc(usertrackingid);
        }
        if(type == ""){
            return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd);
        }
        if(createdAtStart == null && createdAtEnd == null) {
            return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdAndTypeOrderByCreatedAtDesc(usertrackingid, type);
        }
        return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenAndTypeOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd, type);
    }

    @GetMapping("/details/userstrackingTypecreatedAtPagination")
    public Page<MealsTracking> findByuserstrackingIdListActivitiesIdCreatedAtBetweenPagination(@RequestParam(required = false) Long usertrackingid,
                                                                                               @RequestParam(required = false) Instant createdAtStart,
                                                                                               @RequestParam(required = false) Instant createdAtEnd,
                                                                                               @RequestParam(required = false) String type,
                                                                                               @RequestParam(defaultValue = "0") int page,
                                                                                               @RequestParam(defaultValue = "8")int size){
        Pageable paging = PageRequest.of(page, size);
        if(createdAtStart == null && createdAtEnd == null && type == ""){
            return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdOrderByCreatedAtDesc(usertrackingid, paging);
        }
        if(type == ""){
            return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd, paging);
        }
        if(createdAtStart == null && createdAtEnd == null) {
            return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdAndTypeOrderByCreatedAtDesc(usertrackingid, type, paging);
        }
        return mealsTrackingRepository.findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenAndTypeOrderByCreatedAtDesc(usertrackingid, createdAtStart, createdAtEnd, type, paging);
    }

    @GetMapping("/details/userstracking/{userstrackingid}/mealstracking")
    public List<MealsTracking> findByUserstrackingid(@PathVariable("userstrackingid") Long userstrackingid){
        return mealsTrackingService.findByUserstrackingId(userstrackingid);
    }

    @GetMapping("/details/newQ/{userstrackingid}/{start}/{end}")
    public List<MealsTracking> newQ(@PathVariable("userstrackingid") Long userstrackingid,
                                    @PathVariable("start") String start,
                                    @PathVariable("end") String end) throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd").parse(start);
        Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        return mealsTrackingRepository.newQ(userstrackingid,start1,end1);
    }

    @GetMapping("/bieudodinhduong/{mealstrackingid}")
    public Object getBieuDoDinhDuongBuaAn(@PathVariable("mealstrackingid")Long mealstrackingid) {
        Object arr = mealsTrackingRepository.bieuDoDinhDuongBuaAn(mealstrackingid);
        return arr;
    }

    @GetMapping("/bieudodinhduongtheoCreatedAtBetween")
    public Object getBieuDoDinhDuongNhieuBuaAn(@RequestParam() Long usertrackingid,
                                               @RequestParam(defaultValue = "1970-01-01") String start,
                                               @RequestParam(defaultValue = "2030-12-30") String end) throws ParseException {

        Date start1 = new SimpleDateFormat("yyyy-MM-dd").parse(start);
        Date end1 = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        Object arr = mealsTrackingRepository.bieuDoDinhDuongNhieuBuaAn(usertrackingid,start1,end1);
        return arr;
    }

    @GetMapping("/details/userstrackingandcreatedat/{userstrackingid}/{createdatstart}/{createdatend}")
    public Double findFind(@PathVariable("userstrackingid") Long userstrackingid,
                                        @PathVariable("createdatstart") Instant createdatstart,
                                        @PathVariable("createdatend") Instant createdatend){
        return mealsTrackingService.findMealCaloriesServingOneDay(userstrackingid, createdatstart, createdatend);
    }

//    @GetMapping("/details/userstrackingandcreatedatt/{userstrackingid}/{createdatstart}")
//    public List<MealsTracking> findFindd(@PathVariable("userstrackingid") Long userstrackingid,
//                                         @PathVariable("createdatstart") Instant createdatstart){
//        return mealsTrackingRepository.findAllByUsersTrackingIdAndCreatedAtContaining(userstrackingid, createdatstart);
//    }

    @PostMapping("/create/userstracking/{usertrackingid}/mealstracking")
    public MealsTracking createMealsTrackingByUsersTrackingId(@PathVariable("usertrackingid")Long usertrackingid
            ,@Validated @RequestBody MealsTracking mealsTracking){
//        UsersTracking usersTracking = usersTrackingRepository.findById(usertrackingid).
//                orElseThrow(() -> new ResourceAccessException("Not found Userstracking with id = " + usertrackingid));

//        MealsTracking mealsTracking1 = mealsTrackingService.findByDId(mealsTracking.getId());
//        if(mealsTracking1 != null) throw new DuplicateIdException("MealsTracking", mealsTracking.getId());

        mealsTracking.setUsersTracking(usersTrackingRepository.findById(usertrackingid).
                orElseThrow(() -> new ResourceAccessException("Not found Userstracking with id = " + usertrackingid)));
        mealsTracking.setMeal_volume(0.0);
        mealsTracking.setMeal_calories(0.0);
        mealsTracking.setMeal_vitamin_a(0.0);
        mealsTracking.setMeal_iron(0.0);
        mealsTracking.setMeal_calcium(0.0);
        mealsTracking.setMeal_carbohydrates(0.0);
        mealsTracking.setMeal_potassium(0.0);
        mealsTracking.setMeal_sugars(0.0);
        mealsTracking.setMeal_sodium(0.0);
        mealsTracking.setMeal_cholesterol(0.0);
        mealsTracking.setMeal_vitamin_c(0.0);
        mealsTracking.setMeal_protein(0.0);
        mealsTracking.setMeal_trans_fat(0.0);
        mealsTracking.setMeal_potassium(0.0);
        mealsTracking.setMeal_fat(0.0);
        mealsTracking.setMeal_saturated_fat(0.0);
        mealsTracking.setMeal_diatery_fiber(0.0);

        return mealsTrackingService.save(mealsTracking);
//        return mealsTrackingService.save(new MealsTracking(mealsTracking.getId(),mealsTracking.getName(),mealsTracking.getDescription(),mealsTracking.getType(),mealsTracking.getMeal_volume(),mealsTracking.getCreated_at()));
    }

    @PutMapping("update/{id}")
    public MealsTracking updateMealsTrackingById(@PathVariable("id")Long id,
                                                 @Validated @RequestBody MealsTracking mealsTrackingRequest) {
        MealsTracking mealsTracking = mealsTrackingService.findById(id);

        mealsTracking.setName(mealsTrackingRequest.getName());
        if (mealsTrackingRequest.getDescription() != null) {
            mealsTracking.setDescription(mealsTrackingRequest.getDescription());
        }
        if (mealsTrackingRequest.getType() != null) {
            mealsTracking.setType(mealsTrackingRequest.getType());
        }
        if (mealsTrackingRequest.getMeal_volume() != null) {
            mealsTracking.setMeal_volume(mealsTrackingRequest.getMeal_volume());
        }
        if (mealsTrackingRequest.getCreated_at() != null) {
            mealsTracking.setCreated_at(mealsTrackingRequest.getCreated_at());
        }
        return mealsTrackingService.save(mealsTracking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealsTrackingService.delete(id);
        return ResponseEntity.ok().build();
    }
}
