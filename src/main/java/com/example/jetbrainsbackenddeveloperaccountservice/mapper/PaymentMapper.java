package com.example.jetbrainsbackenddeveloperaccountservice.mapper;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.model.Payment;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Component
public class PaymentMapper {

    private final String DATE_FORMATTER = "MM-yyyy";

    public Payment toPayment(PaymentDto paymentDto, User user) {
        return new Payment(
                user,
                LocalDate.from(YearMonth.parse(paymentDto.getPeriod(), DateTimeFormatter.ofPattern(DATE_FORMATTER)).atDay(1)),
                paymentDto.getSalary()
        );
    }
}
