package com.example.bankingapp.controller;

import com.example.bankingapp.dto.LoanCreationDto;
import com.example.bankingapp.dto.LoanPaymentDto;
import com.example.bankingapp.entity.Loan;
import com.example.bankingapp.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoansService loansService;
    @GetMapping
    public ResponseEntity<List<Loan>> getLoans() {
        return ResponseEntity.ok(loansService.getLoans());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable Long id){
        return ResponseEntity.ok(loansService.getLoanById(id));
    }
    @PostMapping
    public ResponseEntity<String> drawUpLoan(@RequestBody LoanCreationDto loanDto){
        loansService.drawUpLoan(loanDto);
        return ResponseEntity.ok("Loan drew up successfully.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> payLoan(@PathVariable Long id, @RequestBody LoanPaymentDto loanPaymentDto){
        loansService.payLoan(id, loanPaymentDto);
        return ResponseEntity.ok("Successfully paid.");
    }
}
