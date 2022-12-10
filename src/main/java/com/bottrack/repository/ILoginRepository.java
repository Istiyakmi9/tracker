package com.bottrack.repository;

import com.bottrack.repositorymodel.UserDetail;

public interface ILoginRepository {

    String authenticateUser(UserDetail userDetail);
}
