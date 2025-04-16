package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//responsestatus should be able to work here instead of in the handler method but doesn't
@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyExistsException extends RuntimeException {

    private String message;

    public AccountAlreadyExistsException() {}

    public AccountAlreadyExistsException(String msg) { 
        super(msg);
        this.message = msg;
    }
    
}