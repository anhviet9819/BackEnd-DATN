package com.example.backend.repository;

import com.example.backend.model.WarningIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarningIndexRepository extends JpaRepository<WarningIndex, Long> {
    WarningIndex findByUsersTrackingId(Long usertrackingid);
}
