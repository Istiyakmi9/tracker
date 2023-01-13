package com.bottrack.authenticationmodule;

import com.bottrack.model.Login;
import com.bottrack.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsServices implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String mobileOrEmail) throws UsernameNotFoundException {
        // Login loginDetail = loginService.authenticateUserService(mobileOrEmail);
        Login loginDetail = new Login();
        loginDetail.setMobile("9995577722");
        loginDetail.setEmail("rahman@gmail.com");
        loginDetail.setPassword("$2a$12$jYnKxkMn5o87JtamUkscNOojb5UDc95kXjQddJakc0mkJbv4/wrR.");

        if (loginDetail.getMobile().equals(mobileOrEmail)  || loginDetail.getEmail().equals(mobileOrEmail)) {
            return new User(
                    loginDetail.getMobile(),
                    loginDetail.getPassword(),
                    new ArrayList<>()
            );
        } else {
            throw new UsernameNotFoundException("Invalid username provided.");
        }
    }
}
