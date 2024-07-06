package com.example.bankingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Min(value = 20000)
    @Max(value = 7000000)
    private Double amount;
    private Double interestRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @JsonIgnore
    @OneToOne(mappedBy = "loan", cascade = CascadeType.ALL)
    private Payment payment;
    @PrePersist
    public void onCreate(){
        if (interestRate == null) {
            interestRate = 0.04;
        }
        startDate = LocalDateTime.now();
    }
}
