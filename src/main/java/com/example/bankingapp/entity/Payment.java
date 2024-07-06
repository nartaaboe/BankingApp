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
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDateTime paymentDate;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;
    @PrePersist
    public void onCreate(){
        paymentDate = LocalDateTime.now();
    }
}