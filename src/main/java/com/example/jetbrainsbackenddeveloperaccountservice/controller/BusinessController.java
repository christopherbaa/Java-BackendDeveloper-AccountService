package com.example.jetbrainsbackenddeveloperaccountservice.controller;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentResponseDto;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.StatusMessage;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.service.PaymentService;
import com.example.jetbrainsbackenddeveloperaccountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    GET api/empl/payment gives access to the employee's payrolls;
    POST api/acct/payments uploads payrolls;
    PUT api/acct/payments updates payment information.
*/
@Validated
@RestController
public class BusinessController {

    private final UserService userService;

    private final PaymentService paymentService;
    private final UserMapper userMapper;

    public BusinessController(@Autowired UserService userService,
                              @Autowired PaymentService paymentService,
                              @Autowired UserMapper userMapper) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.userMapper = userMapper;
    }

    @GetMapping(params = "period", path = "/api/empl/payment")
    public ResponseEntity<PaymentResponseDto> getPayment(@RequestParam("period")
                                                         Optional<String> period,
                                                         Principal user) {

        PaymentResponseDto payment = new PaymentResponseDto();
        if (user != null && period.isPresent()) {
            payment = this.paymentService.getPaymentMapper().toPaymentResponseDto(
                    this.paymentService.getPaymentByEmailAndPeriod(user.getName(), period.get()));
        }
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/api/empl/payment")
    public ResponseEntity<ArrayList<PaymentResponseDto>> getPayments(Principal user) {
        ArrayList<PaymentResponseDto> payments = new ArrayList<>();
        if (user != null) {
            payments = this.paymentService.getPaymentByEmailSortedByPeriodDesc(user.getName()).stream()
                    .map(p -> this.paymentService.getPaymentMapper().toPaymentResponseDto(p))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PostMapping("/api/acct/payments")
    public ResponseEntity<StatusMessage> postPayments(@Valid @RequestBody ArrayList<PaymentDto> payments) {
        this.paymentService.savePayments(payments);
        return new ResponseEntity<>(new StatusMessage("Added successfully!"), HttpStatus.OK);
    }

    @PutMapping("/api/acct/payments")
    public ResponseEntity<StatusMessage> updatePayment(@Valid @RequestBody PaymentDto payment) {
        this.paymentService.updatePayment(payment);
        return new ResponseEntity<>(new StatusMessage("Updated successfully!"), HttpStatus.OK);
    }

}
