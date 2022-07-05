package com.example.jetbrainsbackenddeveloperaccountservice.exception;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException() {
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
