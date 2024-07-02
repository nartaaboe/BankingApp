package com.example.bankingapp.controller;

import com.example.bankingapp.dto.TransferByPhoneRequest;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/{userId}")
    public ResponseEntity<List<Account>> getAccountsById(@PathVariable String userId) {
        return ResponseEntity.ok(accountService.findAccountsByUserId(userId));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findAccountById(id));
    }
    @PostMapping("/{userId}")
    public ResponseEntity<String> createAccount(@PathVariable String userId) {
        accountService.generateAccount(userId);
        return ResponseEntity.ok("Account created.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@RequestBody Account account) {
        accountService.deleteAccount(account); // there is mistake
        return ResponseEntity.ok("Account deleted.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> fillBalance(@PathVariable Long id, @RequestBody Double amount){
        accountService.fillBalance(id, amount);
        return ResponseEntity.ok("Balance filled.");
    }
    @GetMapping("/get/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getTransactions(id));
    }
    @PostMapping("/transfer/byPhoneNumber")
    public ResponseEntity<String> transferByPhoneNumber(@RequestBody TransferByPhoneRequest request) {
        accountService.transferBalanceByPhoneNumber(request);
        return ResponseEntity.ok("Transfer successful by phone number.");
    }
}
