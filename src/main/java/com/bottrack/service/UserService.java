package com.bottrack.service;

import antlr.Utils;
import com.bottrack.filehandler.FileManager;
import com.bottrack.model.Login;
import com.bottrack.repository.FileRepository;
import com.bottrack.repository.LoginRepository;
import com.bottrack.repositorymodel.FileDetail;
import com.bottrack.model.User;
import com.bottrack.repository.UserRepository;
import com.bottrack.serviceinterfaces.ILoginService;
import com.bottrack.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final FileManager fileManager;
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public UserService(
            LoginRepository loginRepository,
            FileRepository fileRepository,
            FileService fileService,
            FileManager fileManager,
            UserRepository userRepository
    ) {
      this.loginRepository = loginRepository;
      this.fileRepository = fileRepository;
      this.fileService = fileService;
      this.fileManager = fileManager;
      this.userRepository = userRepository;
    }

    public String addUserService(User user) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        user.setCreatedOn(date);
        var result = this.userRepository.save(user);
        return "New user has been added";
    }

    private String EncryptPassword(String password) throws Exception {
        if(password == null || password.isEmpty()){
            throw new Exception("Invalid password for new user.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(password);
        return encryptedPassword;
    }

    @Transactional(rollbackFor = Exception.class)
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

        Login login;
        Optional<Login> loginResult = Optional.ofNullable(this.loginRepository.getLoginByUserId(user.getUserId()));
        if(loginResult.isEmpty()) {
            throw  new Exception("Fail to get login detail. Please contact to admin.");
        }

        login = loginResult.get();
        login.setUserName(user.getFirstName() + " " + user.getLastName());
        login.setMobile(user.getMobile());
        login.setEmail(user.getEmail());
        login.setUpdatedBy(user.getUserId());
        login.setUpdatedOn(date);
        login = this.loginRepository.save(login);
        if(login == null) {
            throw new Exception("Fail to update login detail record. Please contact to admin.");
        }

        FileDetail fileDetail = fileManager.uploadFile(file, user.getUserId(), "profile", user.getFilePath());
        fileDetail.setUserId(userId);
        fileService.updateFileDetailByName(fileDetail);
        user.setFilePath(Paths.get(fileDetail.getFilePath(), fileDetail.getFileName() + "." + fileDetail.getExtension()).toString());
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User createUserService(User user) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());

        Login loginDetail;
        String encryptedPassword = EncryptPassword("welcome");
        loginDetail = new Login();
        Optional<Login> existingUser = Optional.ofNullable(this.loginRepository.getLoginLastRecord());
        if(existingUser.isEmpty()) {
            loginDetail.setUserId(1L);
        } else {
            loginDetail.setUserId(existingUser.get().getUserId() + 1);
        }

        loginDetail.setUserName(user.getFirstName() + " " + user.getLastName());
        loginDetail.setMobile(user.getMobile());
        loginDetail.setEmail(user.getEmail());
        loginDetail.setPassword(encryptedPassword);
        loginDetail.setRoleId(1);
        loginDetail.setCreatedOn(date);
        loginDetail.setCreatedBy(0L);

        loginDetail.setUpdatedBy(user.getUserId());
        loginDetail.setUpdatedOn(date);

        loginDetail = this.loginRepository.save(loginDetail);
        if(loginDetail == null)
            throw  new Exception("Fail to create login detail.");

        User currentUser;
        Optional<User> result = this.userRepository.findById(loginDetail.getUserId());
        if (!result.isEmpty()) {
            throw new Exception("Duplicate userid found. Please contact to admin.");
        }

        currentUser = user;
        currentUser.setUserId(loginDetail.getUserId());
        currentUser.setCreatedBy(currentUser.getUserId());
        currentUser.setCreatedOn(date);
        currentUser.setUpdatedOn(date);
        currentUser.setUpdatedBy(currentUser.getUserId());
        user = userRepository.save(user);
        if(user == null)
            throw new Exception("Invalid user id passed.");
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
                result.get().setFilePath(Paths.get(fileDetail.getFilePath(), fileDetail.getFileName() + "." + fileDetail.getExtension()).toString());
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
