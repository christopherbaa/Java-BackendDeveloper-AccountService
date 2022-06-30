package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.PaymentMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final UserService userService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(@Autowired UserService userService,
                              @Autowired PaymentRepository paymentRepository,
                              @Autowired PaymentMapper paymentMapper) {
        this.userService = userService;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public void savePayments(ArrayList<PaymentDto> payments) {
        payments.stream().allMatch(payment -> userService.existsByEmail(payment.getEmail()));
    }
}
