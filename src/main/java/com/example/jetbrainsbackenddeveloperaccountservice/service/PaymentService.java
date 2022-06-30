package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;

import java.util.ArrayList;

public interface PaymentService {
    public void savePayments(ArrayList<PaymentDto> payments);
    public boolean hasValidUser(PaymentDto paymentDto);
    public boolean hasValidPeriod(PaymentDto paymentDto);
    public boolean hasValidSalary(PaymentDto paymentDto);

}
