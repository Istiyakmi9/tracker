package com.bottrack.repositoryinterfaces;

import com.bottrack.model.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@EnableAutoConfiguration
public interface IUserRepository extends JpaRepository<User, Long> {

//    String addUserRepository(User user);
//    String updateUserRepository(User user, long userId);
//    ArrayList<User> getAllUserRepository();
//    public ArrayList<User> getByUserIdRepository(long userId);
//    public String deleteByUserIdRepository(long userId);



}
