package com.example.jetbrainsbackenddeveloperaccountservice.exception;

public class PasswordIsCompromisedException extends RuntimeException {

    public PasswordIsCompromisedException(String message) {
        super(message);
    }

    public PasswordIsCompromisedException() {
    }

}
