package com.example.bankingapp.service;

import com.example.bankingapp.dto.LoanCreationDto;
import com.example.bankingapp.dto.LoanPaymentDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Loan;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class LoansService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountRepository accountRepository;
    public List<Loan> getLoans(){
        return loanRepository.findAll();
    }
    public Loan getLoanById(Long id){
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found."));
    }
    public void drawUpLoan(LoanCreationDto loanDto){
        Loan loan = new Loan();
        loan.setUserId(loanDto.getId());
        List<Account> accounts = accountRepository.findAccountsByUserId(loanDto.getId());
        for(Account account : accounts){
            account.setBalance(account.getBalance() + loanDto.getAmount());
            break;
        }
        loan.setAmount(loanDto.getAmount());
        Pattern pattern = Pattern.compile("(\\d+)(months?)");
        Matcher matcher = pattern.matcher(loanDto.getTerm().toLowerCase());
        if (matcher.matches()) {
            int months = Integer.parseInt(matcher.group(1));
            loan.setEndDate(loan.getStartDate().plusMonths(months));
        } else {
            throw new IllegalArgumentException("Invalid loan duration format. Please use the format like '6months' or '9months'.");
        }
        loanRepository.save(loan);
    }
    public void payLoan(Long id, LoanPaymentDto loanDto){
        Loan loan = getLoanById(id);
        Account account = accountRepository.findById(loanDto.getId()).orElseThrow(() -> new RuntimeException("Account not found."));
        Double amount = loanDto.getAmount();
        if(account.getBalance() >= amount){
            account.setBalance(account.getBalance() - amount);
            if(loan.getAmount() < amount){
                account.setBalance(account.getBalance() - loan.getAmount());
                loanRepository.delete(loan);
            }
            else if(loan.getAmount() > amount){
                account.setBalance(account.getBalance() - amount);
                loan.setAmount(loan.getAmount() - amount);
            }
            else{
                loan
            }
        }
        else{
            throw new RuntimeException("Not enough balance.");
        }
        loan.setAmount(loan.getAmount() - amount);
    }
}
