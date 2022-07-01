package com.example.jetbrainsbackenddeveloperaccountservice.controller;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.PaymentDto;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.StatusMessage;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.Payment;
import com.example.jetbrainsbackenddeveloperaccountservice.service.PaymentService;
import com.example.jetbrainsbackenddeveloperaccountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

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

    @GetMapping("/api/empl/payment")
    public ResponseEntity<UserRegistrationDto> getPayment(Principal user) {
        return  user != null ? new ResponseEntity<>(
                new UserRegistrationDto(this.userService.findUserByEmail(user.getName())),
                HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/api/acct/payments")
    public ResponseEntity<StatusMessage> postPayments(@Valid @RequestBody ArrayList<PaymentDto> payments) {
        this.paymentService.savePayments(payments);
        return new ResponseEntity<>(new StatusMessage("Added successfully!"), HttpStatus.OK);
    }

    // TODO Delete later
    @GetMapping("/api/acct/payments")
    public ArrayList<Payment> getPayments() {
        return null;
    }

}
