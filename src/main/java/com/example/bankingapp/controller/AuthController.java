package com.example.bankingapp.controller;

import com.example.bankingapp.dto.LoginRequest;
import com.example.bankingapp.dto.SignUpRequest;
import com.example.bankingapp.dto.TokenResponse;
import com.example.bankingapp.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signup(signUpRequest));
    }
    @PostMapping("/validate")
    public Boolean isTokenValid(@RequestBody String token){
        return authService.isTokenValid(token);
    }
}
