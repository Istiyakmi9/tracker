package com.bottrack.controller;


import com.bottrack.model.ApiResponse;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.model.User;
import com.bottrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseModal addUser(@RequestBody User user) throws Exception {
        var result = this.userService.addUserService(user);
        return BuildOk(result);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseModal updateUser(@RequestBody User user, @PathVariable("userId") long userId) throws IOException {
        var result = this.userService.updateUserService(user, userId);
        return BuildOk(result);
    }

    @GetMapping("/getAllUser")
    public ResponseModal getAllUser(){
        var result = this.userService.getAllUserService();
        return BuildOk(result);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ApiResponse> getByUserId(@PathVariable ("userId") long userId){
        var result = this.userService.getByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteByUserId/{userId}")
    public ResponseEntity<ApiResponse> deleteByUserId(@PathVariable ("userId") long userId){
        var result = this.userService.deleteByUserIdService(userId);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
