package com.example.service;

// import java.security.GeneralSecurityException; not sure how they want me to use this

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.GenericCustomException;
import com.example.exception.UnauthorizedException;
import com.example.repository.AccountRepository;;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository; //variable injection is sloppy

    @Transactional
    public Account saveAccount(Account acct) throws GenericCustomException, AccountAlreadyExistsException {
        if (acct.getUsername() == "") {
            throw new GenericCustomException("Username must not be blank");
        } else if (acct.getPassword().length() < 4) {
            throw new GenericCustomException("Password must be 4 or more characters");
        } else if (accountRepository.existsByUsername(acct.getUsername())) {
            throw new AccountAlreadyExistsException("Username already exists");
        }
        return accountRepository.save(acct); 
    }

    @Transactional //definitely rollback deletion if there is an error
    public Account findByUsernameAndPassword(Account acct) throws GenericCustomException, UnauthorizedException {
        Account foundAcct = accountRepository.findByUsernameAndPassword(acct.getUsername(), acct.getPassword());
        if (foundAcct != null) {return foundAcct;}
        throw new UnauthorizedException("No username with that password");
    }

} 