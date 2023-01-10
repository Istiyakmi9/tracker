package com.bottrack.service;

import com.bottrack.model.Login;
import com.bottrack.repository.LoginRepository;
import com.bottrack.serviceinterfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginService implements ILoginService {

    @Autowired
    LoginRepository loginRepository;


    public String updateLoginByUserIdService(Login login, long userId) throws IOException {
        var result = "";
        if (userId > 0){
            result = this.loginRepository.updateLoginByUserIdRepository(login, userId);
            if (result == null || result == "")
                throw new IOException("Unable to update Login");
        }
        return result;
    }
}
