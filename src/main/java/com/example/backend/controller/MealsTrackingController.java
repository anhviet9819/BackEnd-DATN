package com.example.backend.controller;

import com.example.backend.exception.DuplicateIdException;
import com.example.backend.model.MealsTracking;
import com.example.backend.model.UsersTracking;
import com.example.backend.repository.FoodRepository;
import com.example.backend.repository.MealsTrackingRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.service.IMealsTrackingService;
import com.example.backend.service.IUsersTrackingService;
import com.example.backend.service.impl.MealsTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

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

    @GetMapping("/details/userstracking/{userstrackingid}/mealstracking")
    public List<MealsTracking> findByUserstrackingid(@PathVariable("userstrackingid") Long userstrackingid){
        return mealsTrackingService.findByUserstrackingId(userstrackingid);
    }

    @PostMapping("/create/userstracking/{usertrackingid}/mealstracking")
    public MealsTracking createMealsTrackingByUsersTrackingId(@PathVariable("usertrackingid")Long usertrackingid
            ,@Validated @RequestBody MealsTracking mealsTracking){
//        UsersTracking usersTracking = usersTrackingRepository.findById(usertrackingid).
//                orElseThrow(() -> new ResourceAccessException("Not found Userstracking with id = " + usertrackingid));

//        MealsTracking mealsTracking1 = mealsTrackingService.findById(mealsTracking.getId());
//        if(mealsTracking1 != null) throw new DuplicateIdException("MealsTracking", mealsTracking.getId());

        mealsTracking.setUsersTracking(usersTrackingRepository.findById(usertrackingid).
                orElseThrow(() -> new ResourceAccessException("Not found Userstracking with id = " + usertrackingid)));
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
