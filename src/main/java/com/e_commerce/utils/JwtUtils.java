package com.e_commerce.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;
    public String generateJwtToken(UserDetailsImpl userDetails){
        
    }
}
