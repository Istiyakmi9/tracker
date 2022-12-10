package com.bottrack.repository;

import com.bottrack.repositorymodel.UserDetail;

public class LoginRepository implements ILoginRepository{

    public String authenticateUser(UserDetail userDetail) {
        return userDetail.getUserName();
    }
}
