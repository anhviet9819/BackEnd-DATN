package com.example.backend.controller;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.backend.model.*;
import com.example.backend.payload.request.LoginRequest;
import com.example.backend.payload.request.SignupRequest;
import com.example.backend.payload.response.JwtResponse;
import com.example.backend.payload.response.MessageResponse;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UsersTrackingRepository;
import com.example.backend.repository.WarningIndexRepository;
import com.example.backend.security.jwt.JwtUtils;
import com.example.backend.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class  AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UsersTrackingRepository usersTrackingRepository;

    @Autowired
    WarningIndexRepository warningIndexRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if(authentication != null){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            System.out.println("Thanh cong");

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles,
                    userDetails.getIs_active()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Message!");

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.setUsersTracking(new UsersTracking(user.getId()));
        user.setIs_active(true);
//        Long id = null;
//        UsersTracking usersTracking = new UsersTracking(id);
//        usersTrackingRepository.save(usersTracking);
        userRepository.save(user);
        WarningIndex warningIndex = new WarningIndex(user.getUsersTracking());
        warningIndexRepository.save(warningIndex);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/updatepassword/{username}")
    public ResponseEntity<?> updatePassword(@PathVariable("username") String username,
                                            @Valid @RequestParam String oldPassword,
                                            @Valid @RequestParam String newPassword){
        User user = userRepository.findByUsername(username);
//        System.out.println(oldPassword);
//        System.out.println(encoder.matches(oldPassword, user.getPassword()));
        if(encoder.matches(oldPassword, user.getPassword()) != true){
                            return ResponseEntity.badRequest().body(new MessageResponse("Old password incorrect!"));
        }
        else{
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Change password successfully!"));
        }
    }

    @PutMapping("/updatestatus/{username}")
    public User updateStatusUser(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username);
        System.out.println(user.getIs_active() == null);
        if(user.getIs_active() == null || user.getIs_active() == false){
            user.setIs_active(true);
            System.out.println(user.getIs_active());
            return userRepository.save(user);
        }
        user.setIs_active(false);
        return userRepository.save(user);
    }

    @PutMapping("/update/{username}")
    public User updateUserProfile(@PathVariable("username") String username,
                                  @RequestBody User userProfileRequest) {
        User user = userRepository.findByUsername(username);
        WarningIndex warningIndex = warningIndexRepository.findByUsersTrackingId(user.getUsersTracking().getId());
        int tempCheck = 0;
        int ageNow = Period.between(user.getBirthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), Date.from(Instant.now()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()).getYears();
        if(user.getName().isEmpty() && user.getBirthday() == null){
            tempCheck = 1;
        }
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setName(userProfileRequest.getName());
        user.setBirthday(userProfileRequest.getBirthday());
        userRepository.save(user);
        if(tempCheck == 1){
            if(ageNow <= 5){
                warningIndex.setHighSystolic(110);
                warningIndex.setHighDiastolic(78);
                warningIndex.setHighDiastolic(78);
                warningIndex.setLowDiastolic(53);
            } else if (ageNow >= 6 && ageNow <= 13) {
                warningIndex.setHighSystolic(115);
                warningIndex.setHighDiastolic(80);
                warningIndex.setHighDiastolic(90);
                warningIndex.setLowDiastolic(60);
            }
            else if (ageNow >= 14 && ageNow <= 19) {
                warningIndex.setHighSystolic(120);
                warningIndex.setHighDiastolic(81);
                warningIndex.setHighDiastolic(105);
                warningIndex.setLowDiastolic(73);
            }
            else if (ageNow >= 20 && ageNow <= 24) {
                warningIndex.setHighSystolic(132);
                warningIndex.setHighDiastolic(83);
                warningIndex.setHighDiastolic(108);
                warningIndex.setLowDiastolic(75);
            }
            else if (ageNow >= 25 && ageNow <= 29) {
                warningIndex.setHighSystolic(133);
                warningIndex.setHighDiastolic(84);
                warningIndex.setHighDiastolic(109);
                warningIndex.setLowDiastolic(76);
            }
            else if (ageNow >= 30 && ageNow <= 34) {
                warningIndex.setHighSystolic(134);
                warningIndex.setHighDiastolic(85);
                warningIndex.setHighDiastolic(110);
                warningIndex.setLowDiastolic(77);
            }
            else if (ageNow >= 35 && ageNow <= 39) {
                warningIndex.setHighSystolic(135);
                warningIndex.setHighDiastolic(86);
                warningIndex.setHighDiastolic(111);
                warningIndex.setLowDiastolic(78);
            }
            else if (ageNow >= 6 && ageNow <= 13) {
                warningIndex.setHighSystolic(137);
                warningIndex.setHighDiastolic(87);
                warningIndex.setHighDiastolic(112);
                warningIndex.setLowDiastolic(79);
            }
            else if (ageNow >= 6 && ageNow <= 13) {
                warningIndex.setHighSystolic(139);
                warningIndex.setHighDiastolic(88);
                warningIndex.setHighDiastolic(115);
                warningIndex.setLowDiastolic(80);
            }
            else if (ageNow >= 6 && ageNow <= 13) {
                warningIndex.setHighSystolic(142);
                warningIndex.setHighDiastolic(89);
                warningIndex.setHighDiastolic(116);
                warningIndex.setLowDiastolic(81);
            }
            else if (ageNow >= 6 && ageNow <= 13) {
                warningIndex.setHighSystolic(144);
                warningIndex.setHighDiastolic(90);
                warningIndex.setHighDiastolic(118);
                warningIndex.setLowDiastolic(82);
            }
            else {
                warningIndex.setHighSystolic(147);
                warningIndex.setHighDiastolic(91);
                warningIndex.setHighDiastolic(121);
                warningIndex.setLowDiastolic(83);
            }
            warningIndex.setHighGlycemic(7.1);
            warningIndex.setLowGlycemic(3.9);
        }
        warningIndexRepository.save(warningIndex);
        return user;

//        return ResponseEntity.ok(new MessageResponse("Update user successfully!"));
    }

    @GetMapping("/details/{username}")
    public User getUserProfileByUserName(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username);
        return user;
    }

    @GetMapping("/search")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/searchwithfilters")
    public List<User> findUserWithFilters(@RequestParam(required = false) Integer rolesId,
                                          @RequestParam(required = false) Boolean isActive){


        if(isActive == null && rolesId == null){
            return userRepository.findAllByOrderByIsActiveAsc();
        }
        else if(isActive == null){
            return userRepository.findAllByRolesIdOrderByIsActiveAsc(rolesId);
        }
        else if(rolesId == null){

            return userRepository.findAllByIsActiveOrderByIsActiveAsc(isActive);
        }
        else {
            return userRepository.findAllByRolesIdAndIsActiveOrderByIsActiveAsc(rolesId, isActive);
        }
    }
}
