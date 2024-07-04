package com.example.bankingapp.dto;

import lombok.Data;

@Data
public class TransferByAccountNumberRequest {
    private Long id;
    private String accountNumber;
    private Double amount;
}
