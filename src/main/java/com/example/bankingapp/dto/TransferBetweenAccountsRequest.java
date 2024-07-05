package com.example.bankingapp.dto;

import lombok.Data;

@Data
public class TransferBetweenAccountsRequest {
    private Pair sender;
    private Pair receiver;
    private Double amount;
}
