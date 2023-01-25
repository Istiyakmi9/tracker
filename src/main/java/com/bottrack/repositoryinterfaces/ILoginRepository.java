package com.bottrack.repositoryinterfaces;

import com.bottrack.model.Login;

import java.util.List;

public interface ILoginRepository {


    public String updateLoginByUserIdRepository(Login login, long userId);
    public Login authenticateUserRepository(String emailOrMobile) throws Exception;
}
