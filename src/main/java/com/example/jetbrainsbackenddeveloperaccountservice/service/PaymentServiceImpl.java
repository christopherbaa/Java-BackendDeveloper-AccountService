package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.NoDistinctPeriodsException;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.NoValidPeriodException;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.PaymentNotFoundException;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.UserDoesNotExistException;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.PaymentMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.Payment;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import com.example.jetbrainsbackenddeveloperaccountservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        if(hasDistinctPeriods(payments)) {
            if(payments.stream().allMatch(p -> hasValidUser(p) && hasPositiveSalary(p))) {
                payments.stream().forEach(p -> this.paymentRepository.save(
                        paymentMapper.toPayment(p, this.userService.findUserByEmail(p.getEmail()))
                ));
            }
        }
    }


    @Override
    public void updatePayment(PaymentDto payment) {
        if(hasValidUser(payment)) {
            Payment paymentToUpdate = this.paymentRepository.findPaymentByUserAndPeriod(
                    this.userService.findUserByEmail(payment.getEmail()),
                    this.paymentMapper.toLocalDate(payment));

            paymentToUpdate.setSalary(payment.getSalary());
            this.paymentRepository.save(paymentToUpdate);
        }

    }

    // TODO Rewrite methods to String(email, period) instead of PaymentDto?
    // TODO Return Optionals

    @Override
    public Payment getPaymentByEmailAndPeriod(String email, String period) {
        if(this.userService.existsByEmail(email) && isValidPeriod(period)) {
            User user = this.userService.findUserByEmail(email);
            if(existsByUserAndPeriod(user, this.paymentMapper.toLocalDate(period))) {
                return this.paymentRepository.findPaymentByUserAndPeriod(user, paymentMapper.toLocalDate(period));
            }
        }
       return new Payment();
    }

    @Override
    public ArrayList<Payment> getPaymentByEmailSortedByPeriodDesc(String email) {
        if(this.userService.existsByEmail(email)) {
            User user = this.userService.findUserByEmail(email);
            return this.paymentRepository.findPaymentsByUserOrderByPeriodDesc(user);
        }
        return new ArrayList<>();
    }

    @Override
    public PaymentMapper getPaymentMapper() {
        return this.paymentMapper;
    }

    @Override
    public boolean existsByUserAndPeriod(User user, LocalDate period) throws PaymentNotFoundException {
        if(!this.paymentRepository.existsPaymentByUserAndPeriod(user, period)) {
            throw new PaymentNotFoundException("Payment not found!");
        }
        return true;
    }

    @Override
    public boolean isValidPeriod(String period) throws NoValidPeriodException {
        if(!period.matches("^(0[1-9]|1[0-2])-(\\d{4})$")) {
            throw new NoValidPeriodException(period + " isn't a valid period.");
        }
        return true;
    }

    // TODO Delete hasPositiveSalary -> Its already checked in PaymentDto
    @Override
    public boolean hasValidUser(PaymentDto paymentDto) throws UserDoesNotExistException {
        if(!this.userService.existsByEmail(paymentDto.getEmail())) {
            throw new UserDoesNotExistException(paymentDto.getEmail() + " doesnt exists.");
        }
        return true;
    }

    @Override
    public boolean hasDistinctPeriods(ArrayList<PaymentDto> paymentDtos) throws NoDistinctPeriodsException {
        String email = "";
        int counter = 0;

        for(PaymentDto pDto : paymentDtos) {
            if(!email.equals(pDto.getEmail())) {
                email = pDto.getEmail();
                String finalEmail = email;
                counter += paymentDtos.stream().filter(p -> p.getEmail().equals(finalEmail))
                        .map(PaymentDto::getPeriod).distinct().count();
            }
        }
        if(counter != paymentDtos.size()) {
            throw new NoDistinctPeriodsException("Payment periods should be distinct!");
        }
        return true;
    }

    @Override
    public boolean hasPositiveSalary(PaymentDto paymentDto) {
        return paymentDto.getSalary() >= 0;
    }

}
