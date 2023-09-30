package com.bottrack.controller;

import com.bottrack.authenticationmodule.JwtTokenHelper;
import com.bottrack.authenticationmodule.JwtUserDetailsServices;
import com.bottrack.model.ApiResponse;
import com.bottrack.model.Login;
import com.bottrack.model.User;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.LoginService;
import com.bottrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUserDetailsServices jwtUserDetailsServices;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/testapi")
    public ResponseEntity<ApiResponse> testApi() {
        return ResponseEntity.ok(ApiResponse.Ok("Api is up and working"));
    }

    @PutMapping("/updateLoginByUserId/{userId}")
    public ResponseEntity<ApiResponse> updateLoginByUserId(@RequestBody Login login, @PathVariable("userId") long userId) {
        var result = this.loginService.updateLoginByUserIdService(login, userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> authenticateUser(@RequestBody Login loginDetail) throws Exception {
        try {
            authenticate(loginDetail);
            final Login login = loginService.getLoginByMobile(loginDetail.getMobile());
            final User user = validateCredential(login, loginDetail);
            String token = jwtTokenHelper.generateToken(loginDetail);
            return ResponseEntity.ok(ApiResponse.Ok(user, token));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new Exception("Invalid username or password.");
        }
    }

    @RequestMapping(value = "/generateResetPasswordOTP", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> generateResetPasswordOTP(@RequestBody Login loginDetail) throws Exception {
        var result = loginService.generateOTPForResetPassword(loginDetail);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody Login loginDetail) throws Exception {
        var result = loginService.resetPasswordWithOTPService(loginDetail);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    private User validateCredential(Login login, Login request) throws Exception {
        User user = null;
        if (login != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(request.getPassword());
            if(!encoder.matches(password, login.getPassword())) {
                user = userService.getByUserMobileService(request.getMobile());
            } else {
                throw new Exception("Invalid username or password.");
            }
        }
        return user;
    }

    private void authenticate(Login loginDetail) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDetail.getMobile(),
                    loginDetail.getPassword()
            ));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS", e);
        }
    }

    private ResponseEntity<ApiResponse> getLoginDetail(){

        return ResponseEntity.ok(ApiResponse.Ok(null));
    }
}
