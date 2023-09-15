package com.bottrack.repository;

import com.bottrack.model.Login;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@EnableAutoConfiguration
public interface LoginRepository extends JpaRepository<Login, Long> {

//    @Query(value = "update Login set userName = :userName where mobile = :mobile")
//    String updateLoginByUserIdRepository(@Param("userName") String userName, @Param("mobile") String mobile);

    @Query(value = "select l from Login l where l.mobile = :mobile")
    Login getLoginByMobile(@Param("mobile") String mobile);

    @Query(value = "select l from Login l where l.userId = :userId")
    Login getLoginByUserId(@Param("userId") Long mobile);

    @Query(value = "select l.* from login l order by l.UserId desc limit 1", nativeQuery = true)
    Login getLoginLastRecord();
    @Query(value = "select l from Login l where l.email = :email")
    Login getLoginByEmail(@Param("email") String email);
    @Modifying
    @Query(value = "delete from login l where l.Email = :email", nativeQuery = true)
    void deleteLoginByEmail(@Param("email") String email);

}
