package com.dag.homerentwebservice.security.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenGenerator {

    @Value("${homerent.jwt.security.app.key}")
    private String APP_KEY;

    @Value("${homerent.jwt.security.expire.time}")
    private Long EXPIRE_TIME;

    public String generateJwtToken(Authentication authentication){

        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        String token = Jwts.builder()
                .setId(Long.toString(jwtUserDetails.getId()))
                .setSubject(jwtUserDetails.getAuthorities().toArray()[0].toString())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_KEY)
                .compact();

        return token;
    }

    public Integer findUserIdByToken(String token){

        Jws<Claims> claimsJws = parseToken(token);

        String userIdStr = claimsJws
                .getBody()
                .getId();

        return Integer.parseInt(userIdStr);
    }

    private Jws<Claims> parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(APP_KEY)
                .parseClaimsJws(token);
        return claimsJws;
    }

    public boolean validateToken(String token){

        boolean isValid;

        try {
            Jws<Claims> claimsJws = parseToken(token);
            isValid = !isTokenExpired(claimsJws);
        } catch (Exception e){
            isValid = false;
        }

        return isValid;
    }

    private boolean isTokenExpired(Jws<Claims> claimsJws) {

        Date expirationDate = claimsJws.getBody().getExpiration();

        return expirationDate.before(new Date());
    }
}
