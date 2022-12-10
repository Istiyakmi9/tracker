package com.bottrack.controller;

import com.bottrack.repositorymodel.UserDetail;
import com.bottrack.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @GetMapping("/message")
    public String getMessage(){
        return loginService.authenticateUser(new UserDetail());
    }
}
