package com.example.jetbrainsbackenddeveloperaccountservice.mapper;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentResponseDto;
import com.example.jetbrainsbackenddeveloperaccountservice.model.Payment;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMapperTest {

    public static final String DATE_FORMATTER = "MM-yyyy";

    PaymentMapper paymentMapper;

    @BeforeEach
    void setUp() {
        paymentMapper = new PaymentMapper();
    }


    @Test
    void paymentToPaymentResponseDto() {
        //given
        Payment payment = new Payment(new User(),
                LocalDate.from(YearMonth.parse("02-2008",
                        DateTimeFormatter.ofPattern(DATE_FORMATTER)).atDay(1)),
                123456L);

        PaymentResponseDto expectedPaymentResponseDto = new PaymentResponseDto(null,
                null,
                "FEBRUARY-2008",
                "1234 dollar(s) 56 cent(s)");

        //when
        PaymentResponseDto actualPaymentResponseDto = paymentMapper.toPaymentResponseDto(payment);

        //then
        assertEquals(expectedPaymentResponseDto, actualPaymentResponseDto);
    }
}