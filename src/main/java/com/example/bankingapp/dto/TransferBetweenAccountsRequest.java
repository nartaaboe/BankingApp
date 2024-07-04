package com.example.bankingapp.dto;

import lombok.Data;

@Data
public class TransferBetweenAccountsRequest {
    private Long senderId;
    private Long receiverId;
    private Double amount;
}
