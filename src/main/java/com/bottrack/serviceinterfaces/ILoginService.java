package com.bottrack.serviceinterfaces;

import com.bottrack.model.Login;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ILoginService {

    ResponseEntity<Object> updateLoginByUserIdService(Login login, long userId);
    Login authenticateUserService(String emailOrMobile);
    Login getLoginByMobile(String mobile);
    String resetPasswordService(Login login) throws Exception;
}
