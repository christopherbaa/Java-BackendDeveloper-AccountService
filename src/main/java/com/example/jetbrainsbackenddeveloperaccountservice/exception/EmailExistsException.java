package com.example.jetbrainsbackenddeveloperaccountservice.exception;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
    }
}
