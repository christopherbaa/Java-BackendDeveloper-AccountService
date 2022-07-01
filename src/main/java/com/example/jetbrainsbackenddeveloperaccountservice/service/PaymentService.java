package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.UserDoesNotExistException;

import java.util.ArrayList;

public interface PaymentService {
    public void savePayments(ArrayList<PaymentDto> payments);
    public boolean hasValidUser(PaymentDto paymentDto);
    public boolean hasDistinctPeriods(ArrayList<PaymentDto> paymentDtos);
    public boolean hasPositiveSalary(PaymentDto paymentDto);

}
