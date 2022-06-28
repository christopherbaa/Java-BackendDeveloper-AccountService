package com.example.jetbrainsbackenddeveloperaccountservice.exception;

public class PasswordMatchesException extends RuntimeException {

    public PasswordMatchesException(String message) {
        super(message);
    }

    public PasswordMatchesException() {
    }
}
