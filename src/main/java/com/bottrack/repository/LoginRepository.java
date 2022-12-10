package com.bottrack.repository;

import com.bottrack.repositorymodel.UserDetail;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository implements ILoginRepository{

    public String authenticateUser(UserDetail userDetail) {
        return userDetail.getUserName();
    }
}
