package com.example.jetbrainsbackenddeveloperaccountservice.exception;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException() {
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
