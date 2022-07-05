package com.example.jetbrainsbackenddeveloperaccountservice.exception;

public class NoValidPeriodException extends RuntimeException {

    public NoValidPeriodException() {
    }

    public NoValidPeriodException(String message) {
        super(message);
    }
}
