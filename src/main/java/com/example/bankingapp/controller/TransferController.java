package com.example.bankingapp.controller;

import com.example.bankingapp.dto.TransferBetweenAccountsRequest;
import com.example.bankingapp.dto.TransferByAccountNumberRequest;
import com.example.bankingapp.dto.TransferByPhoneRequest;
import com.example.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/transfer/byPhoneNumber")
    public ResponseEntity<String> transferByPhoneNumber(@RequestBody TransferByPhoneRequest request) {
        accountService.transferBalanceByPhoneNumber(request);
        return ResponseEntity.ok("Transfer successful by phone number.");
    }
    @PostMapping("/transfer/byAccountNumber")
    public ResponseEntity<String> transferByAccountNumber(@RequestBody TransferByAccountNumberRequest request){
        accountService.transferBalanceByAccountNumber(request);
        return ResponseEntity.ok("Transfer successful by account number.");
    }
    @PostMapping("/transfer/betweenAccounts")
    public ResponseEntity<String> transferBetweenAccounts(@RequestBody TransferBetweenAccountsRequest request){
        return ResponseEntity.ok(accountService.transferBalanceBetweenAccounts(request));
    }
}
