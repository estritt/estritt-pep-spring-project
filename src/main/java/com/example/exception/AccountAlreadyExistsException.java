package com.example.exception;

public class AccountAlreadyExistsException extends RuntimeException {

    private String message;

    public AccountAlreadyExistsException() {}

    public AccountAlreadyExistsException(String msg) { 
        super(msg);
        this.message = msg;
    }
    
}
