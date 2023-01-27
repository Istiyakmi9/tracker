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

//    String addUserRepository(User user);
//    String updateUserRepository(User user, long userId);
//    ArrayList<User> getAllUserRepository();
//    public ArrayList<User> getByUserIdRepository(long userId);
//    public String deleteByUserIdRepository(long userId);

    @Query(value = "select u from User u where u.mobile = :mobile")
    User getByUserMobile(@Param("mobile") String mobile);
}
