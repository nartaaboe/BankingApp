package com.example.bankingapp.service;

import com.example.bankingapp.dto.DepositDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Deposit;
import com.example.bankingapp.entity.User;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.DepositRepository;
import com.example.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    public void saveDeposit(DepositDto depositDto) {
        Account account = accountRepository.findById(depositDto.getId()).orElseThrow(() -> new RuntimeException("Account not found."));
        account.setBalance(account.getBalance() - depositDto.getAmount());
        Deposit deposit = new Deposit();
        deposit.setUserId(account.getUserId());
        deposit.setAmount(depositDto.getAmount());
        depositRepository.save(deposit);
    }
    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }
    public Deposit findDepositById(Long id){
        return depositRepository.findById(id).orElseThrow(() -> new RuntimeException("Deposit not found."));
    }
    public void addBalanceToDeposit(Long id, DepositDto depositDto){
        Account account = accountRepository.findById(depositDto.getId()).orElseThrow(() -> new RuntimeException("Account not found."));
        Double amount = depositDto.getAmount();
        if(account.getBalance() < amount){
            throw new RuntimeException("Not enough balance");
        }
        else{
            account.setBalance(account.getBalance() - depositDto.getAmount());
        }
        Deposit deposit = depositRepository.findById(id).orElseThrow(() -> new RuntimeException("Deposit not found."));
        deposit.setAmount(deposit.getAmount() + depositDto.getAmount());
        depositRepository.save(deposit);
    }
    public void getBalanceToAccount(Long id, DepositDto depositDto){
        Account account = accountRepository.findById(depositDto.getId()).orElseThrow(() -> new RuntimeException("Account not found."));
        Deposit deposit = depositRepository.findById(id).orElseThrow(() -> new RuntimeException("Deposit not found."));
        Double amount = depositDto.getAmount();
        if(deposit.getAmount() < amount){
            throw new RuntimeException("Not enough balance.");
        }
        else{
            if(deposit.getAmount() - amount > 1000){
                deposit.setAmount(deposit.getAmount() - depositDto.getAmount());
            }
            else{
                throw new RuntimeException("Deposit must have at least 1000.");
            }
        }
        account.setBalance(account.getBalance() + depositDto.getAmount());
        depositRepository.save(deposit);
    }
    public void cancelDeposit(Long id){
        Deposit deposit = depositRepository.findById(id).orElseThrow(() -> new RuntimeException("Deposit not found."));
        List<Account> accounts = accountRepository.findAccountsByUserId(deposit.getUserId());
        for(Account account : accounts){
            account.setBalance(account.getBalance() + deposit.getAmount());
            accountRepository.save(account);
            break;
        }
        depositRepository.delete(deposit);
    }
}
