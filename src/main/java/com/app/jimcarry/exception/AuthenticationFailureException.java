package com.app.jimcarry.exception;

public class AuthenticationFailureException extends RuntimeException {
    public AuthenticationFailureException(String message){
        super(message);
    }
}
