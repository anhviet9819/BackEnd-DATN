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
public interface DiabatesMelitiyusRepository extends JpaRepository<DiabatesMelitiyus,Long> {
    List<DiabatesMelitiyus> findAllByUsersTrackingId(Long id);
    void deleteDiabatesMelitiyusByUsersTrackingId(Long id);

    List<DiabatesMelitiyus> findAllByUsersTrackingIdOrderByCreatedAtAsc(Long id);

    List<DiabatesMelitiyus> findAllByUsersTrackingIdOrderByCreatedAtDesc(Long id);

    @Query(value = "select * from diabates_melitiyus where user_tracking_id = :usertrackingid order by created_at desc", nativeQuery = true)
    Page<DiabatesMelitiyus> findAllByUsersTrackingIdOrderByCreatedAtDescPagination(@Param("usertrackingid")Long usertrackingid, Pageable paging);
    @Query(value = "select * from diabates_melitiyus where user_tracking_id = :usertrackingid order by created_at asc", nativeQuery = true)
    Page<DiabatesMelitiyus> findAllByUsersTrackingIdOrderByCreatedAtAscPagination(@Param("usertrackingid")Long usertrackingid, Pageable paging);
    @Query(value = "select * from " +
            "diabates_melitiyus as dm where dm.user_tracking_id = :usertrackingid and DATE(dm.created_at) BETWEEN :starttime and :endtime ORDER BY DATE(dm.created_at) ASC", nativeQuery = true)
    List<DiabatesMelitiyus> bieudoThongke(@Param("usertrackingid")Long usertrackingid, @Param("starttime") Date starttime, @Param("endtime")Date endtime);
}
