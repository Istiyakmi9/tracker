package com.bottrack.controller;

import com.bottrack.authenticationmodule.JwtTokenHelper;
import com.bottrack.authenticationmodule.JwtUserDetailsServices;
import com.bottrack.model.ApiResponse;
import com.bottrack.model.Login;
import com.bottrack.model.User;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PutMapping("/updateLoginByUserId/{userId}")
    public ResponseModal updateLoginByUserId(@RequestBody Login login, @PathVariable("userId") long userId) throws IOException {
        var result = this.loginService.updateLoginByUserIdService(login, userId);
        return BuildOk(result);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> authenticateUser(@RequestBody Login loginDetail) throws Exception {
        authenticate(loginDetail);

        final UserDetails userDetails = jwtUserDetailsServices.loadUserByUsername(loginDetail.getMobile());
        String token = jwtTokenHelper.generateToken(loginDetail);
        return ResponseEntity.ok(new ApiResponse(token));
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
}
