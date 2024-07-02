package com.example.bankingapp.dto;

import com.example.bankingapp.entity.Account;
import lombok.Data;

@Data
public class TransferByPhoneRequest {
    private Long id;
    private String phoneNumber;
    private Double amount;
}
