package com.example.bankingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    private Double amount;
    private Double interestRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @JsonIgnore
    @OneToOne(mappedBy = "loan", cascade = CascadeType.ALL)
    private Payment payment;
}
