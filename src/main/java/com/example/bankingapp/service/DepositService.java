package com.example.bankingapp.service;

import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Deposit;
import com.example.bankingapp.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;
    public void saveDeposit(Deposit deposit) {
        depositRepository.save(deposit);
    }
    public Deposit findDepositById(Long id){
        return depositRepository.findById(id).orElseThrow(() -> new RuntimeException("Deposit not found."));
    }
    public void addBalanceToDeposit(Long id, Double balance){
        Deposit deposit = findDepositById(id);
        deposit.setAmount(deposit.getAmount() + balance);
        depositRepository.save(deposit);
        // incomplete
    }
    public void transferBalanceToAccount(Long id, Double balance, Long accountId){
        Deposit deposit = findDepositById(id);
        if(deposit.getAmount() - balance > 1000)
            deposit.setAmount(deposit.getAmount() - balance);
        else
            throw new RuntimeException("Deposit must at least contain 1000.");
        // incomplete
    }
    public void cancelDeposit(Long id){
        depositRepository.deleteById(id);
    }
}
