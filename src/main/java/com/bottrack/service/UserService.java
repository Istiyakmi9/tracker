package com.bottrack.service;

import com.bottrack.exceptionmanager.HandleException;
import com.bottrack.model.User;
import com.bottrack.repository.UserRepository;
import com.bottrack.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public String addUserService(User user) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        user.setCreatedOn(date);
        var result = this.userRepository.save(user);
        return "New user has been added";
    }

    public User updateUserService(User user, long userId) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        user.setUpdatedOn(date);
        Optional<User> result = this.userRepository.findById(userId);
        if (result.isEmpty())
            throw new Exception("Invalid user id passed.");

        user.setUpdatedBy(result.get().getUpdatedBy());
        user.setCreatedBy(result.get().getCreatedBy());
        user.setUpdatedOn(result.get().getUpdatedOn());
        user.setUpdatedBy(userId);
        var updatedData = userRepository.save(user);
        if(updatedData == null)
            throw new Exception("Invalid user id passed.");
        return updatedData;
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
