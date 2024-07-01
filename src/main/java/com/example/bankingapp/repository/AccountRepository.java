package com.example.bankingapp.repository;

import com.example.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountsByUserId(String userId);
    Optional<Account> findByAccountNumber(String accountNumber);
}
