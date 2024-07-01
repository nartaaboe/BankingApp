package com.example.bankingapp.service;

import com.example.bankingapp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoansService {
    @Autowired
    private LoanRepository loanRepository;
}
