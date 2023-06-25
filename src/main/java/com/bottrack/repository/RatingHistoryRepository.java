package com.bottrack.repository;

import com.bottrack.model.RatingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingHistoryRepository extends JpaRepository<RatingHistory, Long> {
    @Query(nativeQuery = true, value = "select r.* from ratinghistory r order by r.RatingHistoryId desc limit 1")
    RatingHistory getLastRatingHistory();

    @Query(nativeQuery = true, value = "select r.* from ratinghistory r where r.UserId = 1 order by r.FeedbackOn desc")
    List<RatingHistory> findByUserId(@Param("userId") long userId);
}
