package com.bottrack.service;

import com.bottrack.repository.UserRepository;
import com.bottrack.model.User;
import com.bottrack.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public String addUserService(User user) throws Exception {
        var result = this.userRepository.addUserRepository(user);
        return result;
    }

    public String updateUserService(User user, long userId) throws IOException {
        var result = "";
        if (userId > 0){
            result = this.userRepository.updateUserRepository(user, userId);
            if (result == null || result == "")
            throw new IOException("Unable to update");
        }
        return result;
    }

    public ArrayList<User> getAllUserService(){
        var result = this.userRepository.getAllUserRepository();
        return result;
    }
}
