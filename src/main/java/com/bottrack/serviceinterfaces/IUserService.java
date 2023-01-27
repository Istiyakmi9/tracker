package com.bottrack.serviceinterfaces;

import com.bottrack.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    String addUserService(User user) throws Exception;
    ResponseEntity<Object> updateUserService(User user, long userId);
    List<User> getAllUserService();

    Optional<User> getByUserIdService(long userId);
    String deleteByUserIdService(long userId);
    User getByUserMobileService(String mobile);
}
