package com.example.backend.repository;

import com.example.backend.model.BloodPressure;
import com.example.backend.model.DiabatesMelitiyus;
import com.example.backend.model.PersonalIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressure,Long> {
    List<BloodPressure> findAllByUsersTrackingId(Long id);
    void deleteBloodPressureByUsersTrackingId(Long id);

    List<BloodPressure> findAllByUsersTrackingIdOrderByCreatedAtAsc(Long id);

    List<BloodPressure> findAllByUsersTrackingIdOrderByCreatedAtDesc(Long id);

    @Query(value = "select * from blood_pressure where user_tracking_id = :usertrackingid order by created_at desc", nativeQuery = true)
    Page<BloodPressure> findAllByUsersTrackingIdOrderByCreatedAtDescPagination(@Param("usertrackingid")Long usertrackingid, Pageable paging);
    @Query(value = "select * from blood_pressure where user_tracking_id = :usertrackingid order by created_at asc", nativeQuery = true)
    Page<BloodPressure> findAllByUsersTrackingIdOrderByCreatedAtAscPagination(@Param("usertrackingid")Long usertrackingid, Pageable paging);
    @Query(value = "select * from " +
            "blood_pressure as bp where bp.user_tracking_id = :usertrackingid and DATE(bp.created_at) BETWEEN :starttime and :endtime ORDER BY DATE(bp.created_at) ASC", nativeQuery = true)
    List<BloodPressure> bieudoThongke(@Param("usertrackingid")Long usertrackingid, @Param("starttime") Date starttime, @Param("endtime")Date endtime);
}
