package com.e_commerce.e_commerce_microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce_microservices.entity.LoginRequest;
import com.e_commerce.e_commerce_microservices.entity.LoginResponse;
import com.e_commerce.service.MFAService;
import com.e_commerce.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MFAService mfaService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        String secretKey = mfaService.generateSecretKey();

        return ResponseEntity.ok(new LoginResponse(jwt, secretKey));
    }

    @PostMapping("/verifyMFA")
    public ResponseEntity<?> verifyMFA(@RequestParam String secretKey, @RequestParam int code) {
        boolean isVerified = mfaService.verifyCode(secretKey, code);
        if (isVerified) {
            return ResponseEntity.ok("MFA Verified");
        } else {
            return ResponseEntity.status(401).body("Invalid MFA Code");
        }
    }
}
