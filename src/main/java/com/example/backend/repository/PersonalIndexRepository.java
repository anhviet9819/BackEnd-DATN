package com.example.backend.repository;

import com.example.backend.model.MealsTracking;
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
public interface PersonalIndexRepository extends JpaRepository<PersonalIndex,Long> {
    List<PersonalIndex> findAllByUsersTrackingId(Long id);
    List<PersonalIndex> findAllByUsersTrackingIdOrderByCreatedAtDesc(Long id);
    List<PersonalIndex> findAllByUsersTrackingIdOrderByCreatedAtAsc(Long id);

    @Query(value = "select * from personal_index where user_tracking_id = :usertrackingid order by created_at desc", nativeQuery = true)
    Page<PersonalIndex> findAllByUsersTrackingIdOrderByCreatedAtDescPagination(@Param("usertrackingid")Long usertrackingid, Pageable paging);
    @Query(value = "select * from personal_index where user_tracking_id = :usertrackingid order by created_at asc", nativeQuery = true)
    Page<PersonalIndex> findAllByUsersTrackingIdOrderByCreatedAtAscPagination(@Param("usertrackingid")Long usertrackingid, Pageable paging);
    void deletePersonalIndexByUsersTrackingId(Long id);

    @Query(value = "select * from " +
            "personal_index as pi where pi.user_tracking_id = :usertrackingid and DATE(pi.created_at) BETWEEN :starttime and :endtime ORDER BY DATE(pi.created_at) ASC", nativeQuery = true)
    List<PersonalIndex> bieudoThongke(@Param("usertrackingid")Long usertrackingid, @Param("starttime")Date starttime, @Param("endtime")Date endtime);

}
