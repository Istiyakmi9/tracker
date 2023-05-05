package com.bottrack.repository;

import com.bottrack.model.VisitHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitHistoryRepository extends JpaRepository<VisitHistoryModel, Long> {
    @Query(value = "select * from visithistory where UserId= :userId order by VisitedOn Desc limit 30", nativeQuery = true)
    List<VisitHistoryModel> lastMonthHistory(@Param("userId") Long userId);
}
