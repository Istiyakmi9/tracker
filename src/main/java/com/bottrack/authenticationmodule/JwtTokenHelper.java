package com.bottrack.authenticationmodule;

import com.bottrack.model.Login;
import com.bottrack.repositorymodel.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {

    @Value("${jwt.secret}")
    private String secret;

    public static final long JTW_TOKEN_VALIDITY = 1 * 60 * 60;

    public String getMobileNumber(String token) {
        return getClaimedValue(token, Claims::getSubject);
    }

    public Date getExipirationDate(String token) {
        return getClaimedValue(token, Claims::getExpiration);
    }

    public <T> T getClaimedValue(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        final Date expirationDate = getExipirationDate(token);
        return expirationDate.before(new Date());
    }

    public String generateToken(Login loginDetail) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, loginDetail.getMobile());
    }

    public String doGenerateToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JTW_TOKEN_VALIDITY * 100))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, String mobileOrEmail) {
        final String mobile = getMobileNumber(token);
        return (mobile.equals(mobileOrEmail) && !isTokenExpired(token));
    }
}
