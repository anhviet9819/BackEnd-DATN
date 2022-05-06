package com.example.backend.repository;

import com.example.backend.model.UsersTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTrackingRepository extends JpaRepository<UsersTracking,Long> {
}
