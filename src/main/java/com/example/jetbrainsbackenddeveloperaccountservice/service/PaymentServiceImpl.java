package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.PaymentMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final String DATE_FORMATTER = "mm-YYYY";

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
        if(hasDistinctPeriods(payments)) {
            if(payments.stream().allMatch(p -> hasValidUser(p) && hasPositiveSalary(p))) {
                payments.stream().forEach(p -> this.paymentRepository.save(
                        paymentMapper.toPayment(p, this.userService.findUserByEmail(p.getEmail()))
                ));
            }
        }
    }

    // TODO These Should Throw Exceptions
    // TODO Delete hasPositiveSalary -> Its already checked in PaymentDto
    @Override
    public boolean hasValidUser(PaymentDto paymentDto) {
        return this.userService.existsByEmail(paymentDto.getEmail());
    }

    @Override
    public boolean hasDistinctPeriods(ArrayList<PaymentDto> paymentDtos) {
        return paymentDtos.stream().map(PaymentDto::getPeriod).distinct().count() == paymentDtos.size();
    }

    @Override
    public boolean hasPositiveSalary(PaymentDto paymentDto) {
        return paymentDto.getSalary() >= 0;
    }
}
