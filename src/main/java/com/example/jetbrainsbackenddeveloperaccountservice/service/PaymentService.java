package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.PaymentMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.Payment;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;

import java.time.LocalDate;
import java.util.ArrayList;

public interface PaymentService {
    public void savePayments(ArrayList<PaymentDto> payments);
    public boolean hasValidUser(PaymentDto paymentDto);
    public boolean hasDistinctPeriods(ArrayList<PaymentDto> paymentDtos);
    public boolean hasPositiveSalary(PaymentDto paymentDto);
    public void updatePayment(PaymentDto payment);
    public Payment getPaymentByEmailAndPeriod(String email, String period);
    public ArrayList<Payment> getPaymentByEmailSortedByPeriodDesc(String email);
    public PaymentMapper getPaymentMapper();
    public boolean existsByUserAndPeriod(User user, LocalDate period);
    public boolean isValidPeriod(String period);

}
