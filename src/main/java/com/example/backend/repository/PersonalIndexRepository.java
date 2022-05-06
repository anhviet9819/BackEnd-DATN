package com.example.backend.repository;

import com.example.backend.model.PersonalIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalIndexRepository extends JpaRepository<PersonalIndex,Long> {
    List<PersonalIndex> findAllByUsersTrackingId(Long id);
    void deletePersonalIndexByUsersTrackingId(Long id);
}
