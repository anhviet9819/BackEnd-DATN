package com.example.backend.repository;

import com.example.backend.model.DiabatesMelitiyus;
import com.example.backend.model.PersonalIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiabatesMelitiyusRepository extends JpaRepository<DiabatesMelitiyus,Long> {
    List<DiabatesMelitiyus> findAllByUsersTrackingId(Long id);
    void deleteDiabatesMelitiyusByUsersTrackingId(Long id);
}
