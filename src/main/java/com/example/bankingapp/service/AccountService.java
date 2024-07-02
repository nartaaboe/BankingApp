package com.example.bankingapp.service;

import com.example.bankingapp.dto.TransferByPhoneRequest;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.TransactionRepository;
import com.example.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
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
    public void transferBalanceByPhoneNumber(TransferByPhoneRequest transfer){
        Account account = findAccountById(transfer.getId());
        String userId = userRepository.findByPhoneNumber(transfer.getPhoneNumber()).orElseThrow(() -> new RuntimeException("User not found.")).getId();
        List<Account> accs = findAccountsByUserId(userId);
        if(accs.isEmpty())
            throw new RuntimeException("Account not found.");
        Account receiver = accs.get(0);
        Double amount = transfer.getAmount();
        account.setBalance(account.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        Transaction transaction = new Transaction();
        transaction.setTransactionType("PHONE_NUMBER");
        transaction.setAmount(amount);
        transaction.setAccount(account);
        account.getTransactions().add(transaction);
        accountRepository.save(account);
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
    public void fillBalance(Long id, Double amount){
        Account account = findAccountById(id);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
}
