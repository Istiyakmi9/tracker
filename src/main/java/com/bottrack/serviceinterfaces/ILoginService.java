package com.bottrack.serviceinterfaces;

import com.bottrack.model.Login;

import java.io.IOException;

public interface ILoginService {

    public String updateLoginByUserIdService(Login login, long userId) throws IOException;

}
