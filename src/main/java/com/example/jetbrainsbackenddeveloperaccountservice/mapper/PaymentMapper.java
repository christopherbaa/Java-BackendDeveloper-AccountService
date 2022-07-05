package com.example.jetbrainsbackenddeveloperaccountservice.mapper;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentResponseDto;
import com.example.jetbrainsbackenddeveloperaccountservice.model.Payment;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Component
public class PaymentMapper {

     private final String DATE_FORMATTER = "MM-yyyy";

    public Payment toPayment(PaymentDto paymentDto, User user) {
        return new Payment(
                user,
                LocalDate.from(YearMonth.parse(paymentDto.getPeriod(),
                        DateTimeFormatter.ofPattern(DATE_FORMATTER)).atDay(1)),
                paymentDto.getSalary()
        );
    }

    public PaymentResponseDto toPaymentResponseDto(Payment payment) {
        return new PaymentResponseDto(payment.getUser().getName(),
                payment.getUser().getLastname(),
                String.format("%s-%d", capitalizeFirstLetter(payment.getPeriod().getMonth().name()),
                        payment.getPeriod().getYear()),
                String.format("%d dollar(s) %d cent(s)", this.getDollar(payment.getSalary()),
                        this.getCent(payment.getSalary()))
                 );
    }

    private int getDollar(Long salary) {
        return salary.intValue() / 100;
    }

    private int getCent(Long salary) {
        return salary.intValue() % 100;
    }

    public LocalDate toLocalDate(PaymentDto paymentDto) {
        return LocalDate.from(YearMonth.parse(paymentDto.getPeriod(),
                DateTimeFormatter.ofPattern(DATE_FORMATTER)).atDay(1));
    }

    public LocalDate toLocalDate(String date) {
        return LocalDate.from(YearMonth.parse(date,
                DateTimeFormatter.ofPattern(DATE_FORMATTER)).atDay(1));
    }

    public static String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }


}
