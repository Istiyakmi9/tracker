package com.bottrack.authenticationmodule;

import com.bottrack.model.Login;
import com.bottrack.service.LoginService;
import com.bottrack.serviceinterfaces.ILoginService;
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
    private ILoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String mobileOrEmail) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        Login login = loginService.authenticateUserService(mobileOrEmail);
        try {
            if (login.getMobile().equals(mobileOrEmail)  || login.getEmail().equals(mobileOrEmail)) {
                userDetails = new User(
                        login.getMobile(),
                        login.getPassword(),
                        new ArrayList<>()
                );
            } else {
                throw new UsernameNotFoundException("Invalid username provided.");
            }
        }
        catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return userDetails;
    }
}
