package com.bottrack.service;

import com.bottrack.model.Login;
import com.bottrack.repositoryinterfaces.ILoginRepository;
import com.bottrack.serviceinterfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LoginService implements ILoginService {

    @Autowired
    ILoginRepository loginRepository;


    public String updateLoginByUserIdService(Login login, long userId) throws IOException {
        var result = "";
        if (userId > 0){
            result = this.loginRepository.updateLoginByUserIdRepository(login, userId);
            if (result == null || result == "")
                throw new IOException("Unable to update Login");
        }
        return result;
    }

    public List<Login> authenticateUserService(String emailOrMobile) {
        List<Login> logins = null;
        try {
            logins = loginRepository.authenticateUserRepository(emailOrMobile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logins;
    }
}
