package com.example.bankingapp.controller;

import com.example.bankingapp.entity.Loan;
import com.example.bankingapp.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoansService loansService;
    @GetMapping
    public ResponseEntity<List<Loan>> getLoans() {
        return loansService.
    }
}
