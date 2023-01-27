package com.bottrack.service;

import com.bottrack.model.User;
import com.bottrack.repository.UserRepository;
import com.bottrack.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public String addUserService(User user) throws Exception {
        var result = this.userRepository.save(user);
        return "New user has been added";
    }

    public ResponseEntity<Object> updateUserService(User user, long userId) {
        Optional<User> result = this.userRepository.findById(userId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        user.setUserId(userId);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    public List<User> getAllUserService(){
        var result = this.userRepository.findAll();
        return result;
    }

    public Optional<User> getByUserIdService(long userId) {
        var result = this.userRepository.findById(userId);
        return result;
    }

    public User getByUserMobileService(String mobile) {
        var result = this.userRepository.getByUserMobile(mobile);
        return result;
    }

    public String deleteByUserIdService(long userId) {
        this.userRepository.deleteById(userId);
        return "User data has been deleted";
    }
}
