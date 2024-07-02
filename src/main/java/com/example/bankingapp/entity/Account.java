package com.example.bankingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Integer cvv;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double balance;
    @JsonIgnore
    @Column(name = "user_id")
    private String userId;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    @Transient
    private static final SecureRandom random = new SecureRandom();
    @PrePersist
    protected void onCreate(){
        String accountNumber = "";
        for(int i = 0; i < 12; i++){
            accountNumber += random.nextInt(10);
        }
        this.accountNumber = accountNumber;
        cvv = 100 + random.nextInt(900);
        startDate = LocalDateTime.now();
        endDate = LocalDateTime.now().plusYears(5);
        balance = 0.0;
        transactions = new ArrayList<>();
    }
}
