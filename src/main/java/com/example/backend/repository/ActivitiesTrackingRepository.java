package com.example.backend.repository;

import com.example.backend.model.ActivitiesTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ActivitiesTrackingRepository extends JpaRepository<ActivitiesTracking, Long> {
    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingId(Long userstrackingId);
    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingIdOrderByCreatedAtDesc(Long userstrackingId);
    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingIdAndCreatedAtBetween(Long userstrackingId, Instant createdAtStart, Instant createdAtEnd);
    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(Long userstrackingId, Instant createdAtStart, Instant createdAtEnd);

    List<ActivitiesTracking> findActivitiesTrackingByListActivitiesId(Long listActivitiesId);
    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingIdAndListActivitiesId(Long userstrackingId, Long listActivitiesId);
    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingIdAndListActivitiesIdOrderByCreatedAtDesc(Long userstrackingId, Long listActivitiesId);

    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingIdAndCreatedAtBetweenAndListActivitiesIdOrderByCreatedAtDesc(Long userstrackingId, Instant createdAtStart, Instant createdAtEnd, Long listActivitiesId);

    List<ActivitiesTracking> findActivitiesTrackingByOrderByCreatedAtDesc();

    List<ActivitiesTracking> findActivitiesTrackingByUsersTrackingIdAndStartTimeBetween(Long userstrackingId, Instant startTimeStart, Instant startTimeEnd);
    // 23/7/2022, phan trang
    Page<ActivitiesTracking> findByUsersTrackingIdOrderByCreatedAtDesc(Long usertrackingid, Pageable paging);
    Page<ActivitiesTracking> findByUsersTrackingIdAndListActivitiesIdOrderByCreatedAtDesc(Long usertrackingid, Long listactiviesid, Pageable paging);
    Page<ActivitiesTracking> findByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(Long usertrackingid, Instant createdAtStart, Instant createdAtEnd, Pageable paging);
    Page<ActivitiesTracking> findByUsersTrackingIdAndCreatedAtBetweenAndListActivitiesIdOrderByCreatedAtDesc(Long userstrackingId, Instant createdAtStart, Instant createdAtEnd, Long listActivitiesId, Pageable paging);
}
