package com.bottrack.serviceinterfaces;

import com.bottrack.model.User;

import java.io.IOException;
import java.util.ArrayList;

public interface IUserService {

    String addUserService(User user) throws Exception;
    String updateUserService(User user, long userId) throws IOException;
    ArrayList<User> getAllUserService();

}
