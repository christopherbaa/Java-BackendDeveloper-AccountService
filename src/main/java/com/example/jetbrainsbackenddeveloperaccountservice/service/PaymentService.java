package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;

import java.util.ArrayList;

public interface PaymentService {
    public void savePayments(ArrayList<PaymentDto> payments);

}
