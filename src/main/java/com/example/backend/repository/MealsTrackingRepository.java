package com.example.backend.repository;

import com.example.backend.model.MealsTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface MealsTrackingRepository extends JpaRepository<MealsTracking, Long> {
//    List<MealsTracking> findMealsTrackingByFoodsId(Long foodId);
//    List<MealsTracking> findMealsTrackingByMealFoods
    List<MealsTracking> findMealsTrackingByUsersTrackingId(Long userstrackingid);
    List<MealsTracking> findAllByUsersTrackingIdAndCreatedAtBetween(Long userstrackingid, Instant createdAtStart, Instant createdAtEnd);

    List<MealsTracking> findMealsTrackingByUsersTrackingIdOrderByCreatedAtDesc(Long userstrackingid);
    List<MealsTracking> findMealsTrackingByUsersTrackingIdAndTypeOrderByCreatedAtDesc(Long userstrackingid, String type);

    List<MealsTracking> findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(Long userstrackingid, Instant createdAtStart, Instant createdAtEnd);

    List<MealsTracking> findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenAndTypeOrderByCreatedAtDesc(Long userstrackingid, Instant createdAtStart, Instant createdAtEnd, String type);
    @Query(value = "select distinct on(m.user_tracking_id, sub.created_at)  id , 'null' as name, 'null' as description, 'null' as type, m.user_tracking_id, sub.meal_volume, sub.created_at\n" +
            "from (select sum(meal_volume) as meal_volume, user_tracking_id, DATE(created_at) as created_at from meals_tracking group by DATE(created_at), user_tracking_id) as sub\n" +
            "join meals_tracking as m on DATE(m.created_at) = sub.created_at and sub.user_tracking_id = m.user_tracking_id\n" +
            "where m.user_tracking_id = :userTrackingId and m.created_at between :start and :end\n" +
            "order by sub.created_at;", nativeQuery = true)
    List<MealsTracking> newQ (@Param("userTrackingId") Long userTrackingId,@Param("start") Date start,@Param("end") Date end);

    @Query(value = "select round(cast(mt.meal_fat/mt.meal_volume*100 as numeric), 2) as perfat, round(cast(mt.meal_protein/mt.meal_volume*100 as numeric), 2) as perpro, round(cast(mt.meal_carbohydrates/mt.meal_volume*100 as numeric), 2) as percarb, round(cast(mt.meal_diatery_fiber/mt.meal_volume*100 as numeric), 2) as perdia\n" +
            ", round(cast(mt.meal_sugars/mt.meal_volume*100 as numeric), 2) as persu, round(cast(mt.meal_calcium/mt.meal_volume/10 as numeric), 2) as percal, \n" +
            "(100 - round(cast(mt.meal_fat/mt.meal_volume*100 as numeric), 2) - round(cast(mt.meal_protein/mt.meal_volume*100 as numeric), 2) - round(cast(mt.meal_diatery_fiber/mt.meal_volume*100 as numeric), 2)\n" +
            "- round(cast(mt.meal_sugars/mt.meal_volume*100 as numeric), 2) - round(cast(mt.meal_calcium/mt.meal_volume/10 as numeric), 2) - round(cast(mt.meal_carbohydrates/mt.meal_volume*100 as numeric), 2)) as diff\n" +
            "from meals_tracking mt where id = :mealstrackingid", nativeQuery = true)
    Object bieuDoDinhDuongBuaAn(@Param("mealstrackingid") Long mealstrackingid);

    @Query(value = "select round(cast(total.meal_fat/total.meal_volume*100 as numeric), 2) as perfat, round(cast(total.meal_protein/total.meal_volume*100 as numeric), 2) as perpro, round(cast(total.meal_carbohydrates/total.meal_volume*100 as numeric), 2) as percarb, round(cast(total.meal_diatery_fiber/total.meal_volume*100 as numeric), 2) as perdia\n" +
            ", round(cast(total.meal_sugars/total.meal_volume*100 as numeric), 2) as persu, round(cast(total.meal_calcium/total.meal_volume/10 as numeric), 2) as percal, \n" +
            "(100 - round(cast(total.meal_fat/total.meal_volume*100 as numeric), 2) - round(cast(total.meal_protein/total.meal_volume*100 as numeric), 2) - round(cast(total.meal_diatery_fiber/total.meal_volume*100 as numeric), 2)\n" +
            "- round(cast(total.meal_sugars/total.meal_volume*100 as numeric), 2) - round(cast(total.meal_calcium/total.meal_volume/10 as numeric), 2) - round(cast(total.meal_carbohydrates/total.meal_volume*100 as numeric), 2)) as diff\n" +
            "from (select sum(meal_volume) meal_volume, sum(meal_fat) meal_fat, sum(meal_protein) meal_protein, sum(meal_carbohydrates) meal_carbohydrates,\n" +
            "sum(meal_sugars) meal_sugars, sum(meal_calcium) meal_calcium, sum(meal_diatery_fiber) meal_diatery_fiber\n" +
            "from meals_tracking where DATE(created_at) >= :start and DATE(created_at) <= :end and user_tracking_id = :usertrackingid) as total", nativeQuery = true)
    Object bieuDoDinhDuongNhieuBuaAn(@Param("usertrackingid")Long usertrackingid,
                                     @Param("start")Date start,
                                     @Param("end")Date end);
    Page<MealsTracking> findMealsTrackingByUsersTrackingIdOrderByCreatedAtDesc(Long userstrackingid, Pageable paging);
    Page<MealsTracking> findMealsTrackingByUsersTrackingIdAndTypeOrderByCreatedAtDesc(Long userstrackingid, String type, Pageable paging);

    Page<MealsTracking> findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenOrderByCreatedAtDesc(Long userstrackingid, Instant createdAtStart, Instant createdAtEnd, Pageable paging);

    Page<MealsTracking> findMealsTrackingByUsersTrackingIdAndCreatedAtBetweenAndTypeOrderByCreatedAtDesc(Long userstrackingid, Instant createdAtStart, Instant createdAtEnd, String type, Pageable paging);
}
