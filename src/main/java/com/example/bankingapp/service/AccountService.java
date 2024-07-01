package com.example.bankingapp.service;

import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.entity.User;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;
    public List<Account> findAccountsByUserId(String userId) {
        return accountRepository.findAccountsByUserId(userId);
    }
    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found."));
    }
    public void generateAccount(String userId) {
        Account account = new Account();
        account.setUserId(userId);
        System.out.println(account);
        accountRepository.save(account);
    }
    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }
    public void transferBalanceByPhoneNumber(){

    }
    public void transferBalanceByAccountNumber(Long id, String accountNumber, Double balance){
        Account sender = findAccountById(id);
        Account receiver = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account not found."));
        sender.setBalance(sender.getBalance() - balance);
        receiver.setBalance(receiver.getBalance() + balance);
        Transaction transaction = new Transaction();
        accountRepository.save(sender);
        accountRepository.save(receiver);
    }
    public List<Transaction> getTransactions(Long id){
        return findAccountById(id).getTransactions();
    }
}
