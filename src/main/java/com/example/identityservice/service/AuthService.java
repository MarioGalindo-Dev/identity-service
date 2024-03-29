package com.example.identityservice.service;

import com.example.identityservice.entity.UserCredential;
import com.example.identityservice.repository.UserCredentialRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserCredentialRepository userCredentialRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    AuthService(UserCredentialRepository userCredentialRepository,
                PasswordEncoder passwordEncoder,
                JwtService jwtService)
    {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public String saveUser(UserCredential userCredential)
    {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
        return "user added to the system";
    }
    public String generateToken(String userName)
    {
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token)throws JwtException
    {
        jwtService.validateToken(token);
    }
}
