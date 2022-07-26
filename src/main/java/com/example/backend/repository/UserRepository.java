package com.example.backend.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByUsername(String username);

    List<User> findAllByRolesIdAndIsActiveOrderByIsActiveAsc(Integer rolesId, Boolean isActive);

    List<User> findAllByRolesIdOrderByIsActiveAsc(Integer rolesId);

    List<User> findAllByIsActiveOrderByIsActiveAsc(Boolean isActive);
    List<User> findAllByOrderByIsActiveAsc();

    User findByUsersTrackingId(Long userTrackingId);
}
