package com.bottrack.serviceinterfaces;

import com.bottrack.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    String addUserService(User user) throws Exception;
    public ResponseEntity<Object> updateUserService(User user, long userId);
    public List<User> getAllUserService();

    public Optional<User> getByUserIdService(long userId);
    public String deleteByUserIdService(long userId);
}
