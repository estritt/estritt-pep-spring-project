package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.example.entity.*;
import com.example.exception.AccountAlreadyExistsException;
import com.example.service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {

        Account addedAccount = accountService.add(account);
        if (addedAccount != null) {
            return ResponseEntity.status(200).body(account);
        }

        return ResponseEntity.status(400).body(account); //hopefully conflict is hit before this

    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
        return ex.getMessage();
    }

}
