package com.example.bankingapp.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;


public class User {
    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
