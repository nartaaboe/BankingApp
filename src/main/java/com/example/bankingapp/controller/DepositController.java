package com.example.bankingapp.controller;

import com.example.bankingapp.dto.DepositDto;
import com.example.bankingapp.entity.Deposit;
import com.example.bankingapp.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private DepositService depositService;
    @PostMapping
    public ResponseEntity<String> create(@RequestBody DepositDto depositDto){
        depositService.saveDeposit(depositDto);
        return ResponseEntity.ok("Deposit saved.");
    }
    @GetMapping
    public ResponseEntity<List<Deposit>> getAllDeposits(){
        return ResponseEntity.ok(depositService.getAllDeposits());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Deposit> getDeposit(@PathVariable Long id){
        return ResponseEntity.ok(depositService.findDepositById(id));
    }
    @PutMapping("/{id}/add")
    public ResponseEntity<String> addBalance(@PathVariable Long id, @RequestBody DepositDto depositDto){
        depositService.addBalanceToDeposit(id, depositDto);
        return ResponseEntity.ok("Balance added.");
    }
    @PutMapping("/{id}/get")
    public ResponseEntity<String> getBalance(@PathVariable Long id, @RequestBody DepositDto depositDto){
        depositService.getBalanceToAccount(id, depositDto);
        return ResponseEntity.ok("Balance retrieved.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeposit(@PathVariable Long id){
        depositService.cancelDeposit(id);
        return ResponseEntity.ok("Deposit deleted.");
    }
}
