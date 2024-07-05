package com.example.bankingapp.service;

import com.example.bankingapp.entity.Loan;
import com.example.bankingapp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoansService {
    @Autowired
    private LoanRepository loanRepository;
    public List<Loan> getLoans(){
        return loanRepository.findAll();
    }
    public Loan getLoanById(Long id){
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found."));
    }
    public void drawUpLoan(Loan loan){

    }
}
