package com.example.jetbrainsbackenddeveloperaccountservice.repository;

import com.example.jetbrainsbackenddeveloperaccountservice.model.Payment;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface PaymentRepository extends CrudRepository <Payment, Long> {
    Payment findPaymentByUserAndPeriod(User user, LocalDate period);
    ArrayList<Payment> findPaymentsByUserOrderByPeriodDesc(User user);
    boolean existsPaymentByUserAndPeriod(User user, LocalDate period);
}




