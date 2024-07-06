package com.example.bankingapp.dto;

import lombok.Data;

@Data
public class LoanCreationDto {
    private String id;
    private Double amount;
    private String term;
}
