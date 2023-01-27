package com.bottrack.service;

import com.bottrack.model.User;
import com.bottrack.repositoryinterfaces.IUserRepository;
import com.bottrack.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    public String addUserService(User user) throws Exception {
        var result = this.iUserRepository.save(user);
        return "New user has been added";
    }

    public ResponseEntity<Object> updateUserService(User user, long userId) {
        Optional<User> result = this.iUserRepository.findById(userId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        user.setUserId(userId);
        iUserRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    public List<User> getAllUserService(){
        var result = this.iUserRepository.findAll();
        return result;
    }

    public Optional<User> getByUserIdService(long userId) {
        var result = this.iUserRepository.findById(userId);
        return result;
    }

    public String deleteByUserIdService(long userId) {
        this.iUserRepository.deleteById(userId);
        return "User data has been deleted";
    }
}
