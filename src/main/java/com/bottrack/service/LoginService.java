package com.bottrack.service;

import com.bottrack.model.Login;
import com.bottrack.repository.LoginRepository;
import com.bottrack.serviceinterfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements ILoginService {

    @Autowired
    LoginRepository loginRepository;


    public ResponseEntity<Object> updateLoginByUserIdService(Login login, long userId){
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        login.setCreatedOn(date);
        Optional<Login> result = this.loginRepository.findById(userId);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        login.setUserId(userId);
        loginRepository.save(login);
        return ResponseEntity.noContent().build();
    }

    public Login authenticateUserService(String emailOrMobile) {
        Login login = null;
        try {
            login = loginRepository.getLoginByMobile(emailOrMobile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return login;
    }

    public Login getLoginByMobile(String mobile) {
        Login login = null;
        try {
            login = loginRepository.getLoginByMobile(mobile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return login;
    }
}
