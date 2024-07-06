package com.example.bankingapp.service.auth;

import com.example.bankingapp.dto.LoginRequest;
import com.example.bankingapp.dto.SignUpRequest;
import com.example.bankingapp.dto.TokenResponse;
import com.example.bankingapp.entity.User;
import com.example.bankingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    public TokenResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        return new TokenResponse(token, "");
    }
    public TokenResponse signup(SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setRole(signUpRequest.getRole());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        userService.save(user);
        return login(new LoginRequest(signUpRequest.getUsername(), signUpRequest.getPassword()));
    }
    public boolean isTokenValid(String token){
        return jwtUtils.isTokenValid(token) && !jwtUtils.isTokenExpired(token);
    }
}
