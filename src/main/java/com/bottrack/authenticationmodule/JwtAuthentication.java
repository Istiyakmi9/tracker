package com.bottrack.authenticationmodule;

import org.springframework.beans.factory.annotation.Value;

public class JwtAuthentication  {

    @Value("jwt.secret")
    private String secret;
}
