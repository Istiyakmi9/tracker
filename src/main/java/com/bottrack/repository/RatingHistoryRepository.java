package com.bottrack.repository;

import com.bottrack.model.RatingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingHistoryRepository extends JpaRepository<RatingHistory, Long> {
    @Query(nativeQuery = true, value = "select r.* from ratinghistory r order by r.RatingHistoryId desc limit 1")
    RatingHistory getLastRatingHistory();

    RatingHistory findByUserId(long userId);
}
