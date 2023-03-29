package com.bottrack.service;

import com.bottrack.filehandler.FileManager;
import com.bottrack.repository.FileRepository;
import com.bottrack.repositorymodel.FileDetail;
import com.bottrack.model.User;
import com.bottrack.repository.UserRepository;
import com.bottrack.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FileManager fileManager;
    @Autowired
    FileService fileService;
    @Autowired
    FileRepository fileRepository;

    public String addUserService(User user) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        user.setCreatedOn(date);
        var result = this.userRepository.save(user);
        return "New user has been added";
    }

    @Transactional( rollbackFor = Exception.class)
    public User updateUserService(User user, MultipartFile file, long userId) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        user.setUpdatedOn(date);
        Optional<User> result = this.userRepository.findById(userId);
        if (result.isEmpty())
            throw new Exception("Invalid user id passed.");

        user.setCreatedBy(result.get().getCreatedBy());
        user.setUpdatedOn(result.get().getUpdatedOn());
        user.setCreatedOn(result.get().getCreatedOn());
        user.setUpdatedBy(userId);
        user = userRepository.save(user);
        if(user == null)
            throw new Exception("Invalid user id passed.");

        FileDetail fileDetail = fileManager.uploadFile(file, user.getUserId(), "profile");
        fileDetail.setUserId(userId);
        fileService.updateFileDetailByName(fileDetail);
        return user;
    }

    public List<User> getAllUserService(){
        var result = this.userRepository.findAll();
        return result;
    }

    public Optional<User> getByUserIdService(long userId) {
        var result = this.userRepository.findById(userId);
        if(result != null) {
            var fileDetail = this.fileRepository.filterByName(result.get().getUserId(), "profile");
            if(fileDetail != null) {
                result.get().setFilePath(fileDetail.getFilePath());
            }
        }
        return result;
    }

    public User getByUserMobileService(String mobile) {
        var result = this.userRepository.getByUserMobile(mobile);
        if(result != null) {
            var fileDetail = this.fileRepository.filterByName(result.getUserId(), "profile");
            if(fileDetail != null) {
                result.setFilePath(fileDetail.getFilePath());
            }
        }
        return result;
    }

    public String deleteByUserIdService(long userId) {
        this.userRepository.deleteById(userId);
        return "User data has been deleted";
    }
}
