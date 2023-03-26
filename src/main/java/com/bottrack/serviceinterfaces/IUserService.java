package com.bottrack.serviceinterfaces;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    String addUserService(User user) throws Exception;
    User updateUserService(User user, MultipartFile file, long userId) throws Exception;
    List<User> getAllUserService();

    Optional<User> getByUserIdService(long userId);
    String deleteByUserIdService(long userId);
    User getByUserMobileService(String mobile);
}
