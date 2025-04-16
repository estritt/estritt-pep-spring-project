package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    private String message;

    public UnauthorizedException() {}

    public UnauthorizedException(String msg) { 
        super(msg);
        this.message = msg;
    }
    
}