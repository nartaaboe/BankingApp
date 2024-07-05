package com.example.bankingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @NotNull(message = "Balance cannot be null.")
    @Min(value = 1001, message = "Deposit balance must be greater than 1000.")
    private Double amount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @PrePersist
    public void onCreate(){
        startDate = LocalDateTime.now();
        endDate = LocalDateTime.now().plusYears(1);
    }
}
