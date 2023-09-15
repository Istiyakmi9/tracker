package com.bottrack.repository;

import com.bottrack.model.User;
import com.bottrack.repositorymodel.FileDetail;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@EnableAutoConfiguration
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.mobile = :mobile")
    User getByUserMobile(@Param("mobile") String mobile);

    @Query(nativeQuery = true, value = "select u.* from user u order by u.UserId desc limit 1")
    User getLastUser();

    @Query("select u from User u where u.email = :email")
    User getUserByEmail(@Param("email") String email);
    @Modifying
    @Query(value = "delete from user u where u.Email = :email", nativeQuery = true)
    void deleteUserByEmail(@Param("email") String email);
}
