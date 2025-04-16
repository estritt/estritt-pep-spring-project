package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//should probably be called customgenericexception if anything
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenericCustomException extends RuntimeException {

    private String message;

    public GenericCustomException() {}

    public GenericCustomException(String msg) { 
        super(msg);
        this.message = msg;
    }
    
}