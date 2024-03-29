package com.example.identityservice.controller;

import com.example.identityservice.dto.AuthRequest;
import com.example.identityservice.entity.UserCredential;
import com.example.identityservice.service.AuthService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService service;
    private AuthenticationManager authenticationManager;
    AuthController(AuthService service,
                   AuthenticationManager authenticationManager){
        this.service = service;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/register")
     public String addNewUser(@RequestBody UserCredential user)
     {
         return service.saveUser(user);
     }

     @PostMapping("/token")
     public String getToken(@RequestBody AuthRequest authRequest)
     {
        Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(authRequest.getName(),authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return service.generateToken(authRequest.getName());
        }
        else {
            throw new RuntimeException("invalid access");
        }
     }
    @GetMapping("/validate")
    public String getToken(@RequestParam("token") String token)
    {
        try{
         service.validateToken(token);
        // Token is valid
        } catch (JwtException ex) {
            // Token is invalid
            ex.printStackTrace();
            return "is not a valid token!";
         }
         return "is a valid token!";
    }

}
