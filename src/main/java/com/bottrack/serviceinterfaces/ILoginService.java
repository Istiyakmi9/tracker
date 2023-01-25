package com.bottrack.serviceinterfaces;

import com.bottrack.model.Login;

import java.io.IOException;
import java.util.List;

public interface ILoginService {

    String updateLoginByUserIdService(Login login, long userId) throws IOException;
    Login authenticateUserService(String emailOrMobile);
}
