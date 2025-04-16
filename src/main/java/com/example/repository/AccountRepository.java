package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Boolean existsByUsername(String username);
    Account findByUsername(String username);
    // Account save(Account acct); i guess i have to define the other ones but this is included by default
    Account findByUsernameAndPassword(String username, String password);

    
}