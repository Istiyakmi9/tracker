package com.bottrack.repository;

import com.bottrack.model.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableAutoConfiguration
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.mobile = :mobile")
    User getByUserMobile(@Param("mobile") String mobile);

    @Query(nativeQuery = true, value = "select u.* from user u order by u.UserId desc limit 1")
    User getLastUser();
}
