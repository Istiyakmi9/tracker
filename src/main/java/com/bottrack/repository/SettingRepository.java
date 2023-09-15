package com.bottrack.repository;

import com.bottrack.model.Setting;
import com.bottrack.model.User;
import com.bottrack.model.VisitHistoryModel;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableAutoConfiguration
public interface SettingRepository extends JpaRepository<Setting, Long> {
    @Query(nativeQuery = true, value = "select s.* from setting s order by s.SettingId desc limit 1")
    Setting getLastSetting();
    @Query(value = "select s from Setting s where s.userId= :userId")
    Setting getSettingByUserId(@Param("userId") int userId);
}
