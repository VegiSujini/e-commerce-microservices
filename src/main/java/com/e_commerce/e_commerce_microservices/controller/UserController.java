package com.e_commerce.e_commerce_microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.dto.SignupRequest;
import com.e_commerce.e_commerce_microservices.entity.MessageResponse;
import com.e_commerce.e_commerce_microservices.entity.User;
import com.e_commerce.e_commerce_microservices.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUSer(@RequestBody SignupRequest singnUpRequest){
        if(userRepository.existsByUsername(singnUpRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken")); 
        }
        User user = new User(singnUpRequest.getUsername(),singnUpRequest.getEmail(),passwordEncoder.encode(singnUpRequest.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("user registered successfully"));
    }
}
