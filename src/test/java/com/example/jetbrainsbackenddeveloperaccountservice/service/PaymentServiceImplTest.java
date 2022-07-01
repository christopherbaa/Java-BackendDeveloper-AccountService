package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    UserService userService;
    @Mock
    PaymentRepository paymentRepository;
    @InjectMocks
    PaymentServiceImpl paymentService;

    ArrayList<PaymentDto> paymentsWithDistinctPeriodsAndPositiveSalary;
    ArrayList<PaymentDto> paymentsWithoutDistinctPeriodsAndNegativeSalary;

    @BeforeEach
    void setUp() {

        paymentsWithDistinctPeriodsAndPositiveSalary = new ArrayList<>(List.of(
                new PaymentDto("test@test.com", "12-2008", 1000L),
                new PaymentDto("johndoe@acme.com", "13-2008", 1000L)
        ));

        paymentsWithoutDistinctPeriodsAndNegativeSalary = new ArrayList<>(List.of(
                new PaymentDto("test@test.com", "13-2008", -1L),
                new PaymentDto("johndoe@acme.com", "13-2008", -1L)
        ));
    }

    @Test
    void hasDistinctPeriods_ShouldBeTrue() {
        assertTrue(paymentService.hasDistinctPeriods(paymentsWithDistinctPeriodsAndPositiveSalary));
    }

    @Test
    void hasDistinctPeriods_ShouldBeFalse() {
        assertFalse(paymentService.hasDistinctPeriods(paymentsWithoutDistinctPeriodsAndNegativeSalary));
    }


    @Test
    void hasPositiveSalary_ShouldBeTrue() {
        assertTrue(paymentService.hasPositiveSalary(paymentsWithDistinctPeriodsAndPositiveSalary.get(0)));
    }

    @Test
    void hasPositiveSalary_ShouldBeFalse() {
        assertFalse(paymentService.hasPositiveSalary(paymentsWithoutDistinctPeriodsAndNegativeSalary.get(0)));
    }
}