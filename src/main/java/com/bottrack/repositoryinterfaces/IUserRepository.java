package com.bottrack.repositoryinterfaces;

import com.bottrack.model.User;

import java.util.ArrayList;

public interface IUserRepository {

    String addUserRepository(User user);
    String updateUserRepository(User user, long userId);
    ArrayList<User> getAllUserRepository();

}
