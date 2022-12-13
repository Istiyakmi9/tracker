package com.bottrack.service;

import com.bottrack.repository.ILoginRepository;
import com.bottrack.repositorymodel.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService{

    @Autowired
    ILoginRepository loginRepository;

    public String authenticateUser(UserDetail userDetail) {

        return loginRepository.authenticateUser(userDetail);
    }


}
