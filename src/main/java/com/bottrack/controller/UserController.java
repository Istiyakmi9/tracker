package com.bottrack.controller;


import com.bottrack.model.ApiResponse;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.model.User;
import com.bottrack.service.UserService;
import com.bottrack.serviceinterfaces.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    IUserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> addUser(@RequestBody User user) throws Exception {
        var result = this.userService.addUserService(user);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@RequestParam("user-model") String userModel,
                                                  @RequestParam("file")MultipartFile file,
                                                  @PathVariable("userId") long userId) throws Exception {

        User user = objectMapper.readValue(userModel, User.class);
        var result = this.userService.updateUserService(user, file, userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) throws Exception {
        var result = this.userService.createUserService(user);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse> getAllUser(){
        var result = this.userService.getAllUserService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ApiResponse> getByUserId(@PathVariable ("userId") long userId){
        var result = this.userService.getByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable ("email") String email){
        var result = this.userService.getUserByEmailService(email);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteByUserId/{userId}")
    public ResponseEntity<ApiResponse> deleteByUserId(@PathVariable ("userId") long userId){
        var result = this.userService.deleteByUserIdService(userId);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
