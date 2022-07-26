package com.example.backend.repository;

import com.example.backend.model.ListActivities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListActivitiesRepository extends JpaRepository<ListActivities,Long> {
    ListActivities findByName(String name);
    Page<ListActivities> findListActivitiesByNameContainingOrderById(Pageable pageable, String name);

    Page<ListActivities> findListActivitiesByNameContainingOrderByCaloPerHourDesc(Pageable pageable, String name);
    Page<ListActivities> findListActivitiesByNameContainingOrderByCaloPerHourAsc(Pageable pageable, String name);
//    Page<ListActivities> findListActivitiesByNameContainingOrderByIdCaloPerHourDesc(Pageable pageable, String name);

}
