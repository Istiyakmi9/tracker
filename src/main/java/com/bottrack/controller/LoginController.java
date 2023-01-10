package com.bottrack.controller;

import com.bottrack.model.Login;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    LoginService loginService;

    @PutMapping("/updateLoginByUserId/{userId}")
    public ResponseModal updateLoginByUserId(@RequestBody Login login, @PathVariable("userId") long userId) throws IOException {
        var result = this.loginService.updateLoginByUserIdService(login, userId);
        return BuildOk(result);
    }


}
