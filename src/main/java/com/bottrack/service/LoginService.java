package com.bottrack.service;

import com.bottrack.model.Login;
import com.bottrack.repository.LoginRepository;
import com.bottrack.serviceinterfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public String resetPasswordService(Login login) throws Exception {
        if (login.getEmail().isEmpty())
            throw new Exception("invalid email id");

        if (login.getPassword().isEmpty())
            throw new Exception("invalid password");

        var loginDetail = loginRepository.getLoginByEmail(login.getEmail());
        if (loginDetail == null)
            throw new Exception("Invalid email entered");

        loginDetail.setPassword(EncryptPassword(login.getPassword()));
        loginRepository.save(loginDetail);
        return "Password reset successfully";
    }

    private String EncryptPassword(String password) throws Exception {
        if(password == null || password.isEmpty()){
            throw new Exception("Invalid password for new user.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
