package com.bottrack.service;

import com.bottrack.repositorymodel.UserDetail;

public interface ILoginService {

    String authenticateUser(UserDetail userDetail);

}
