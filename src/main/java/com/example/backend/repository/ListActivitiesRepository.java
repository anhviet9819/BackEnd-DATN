package com.example.backend.repository;

import com.example.backend.model.ListActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListActivitiesRepository extends JpaRepository<ListActivities,Long> {

}
