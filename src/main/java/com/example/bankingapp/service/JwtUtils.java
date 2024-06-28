package com.example.bankingapp.service;

import com.example.bankingapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class JwtUtils {
    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("username", userDetails.getUsername())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    public boolean isTokenValid(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return jwt != null;
        } catch (Exception e) {
            log.error("Error validating JWT token: {}", e.getMessage());
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            Instant expiration = jwt.getExpiresAt();
            return expiration != null && expiration.isBefore(Instant.now());
        } catch (Exception e) {
            log.error("Error checking JWT token expiration: {}", e.getMessage());
            return true;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return jwt.getClaim("username");
        } catch (Exception e) {
            log.error("Error retrieving username from JWT token: {}", e.getMessage());
            return null;
        }
    }
}
