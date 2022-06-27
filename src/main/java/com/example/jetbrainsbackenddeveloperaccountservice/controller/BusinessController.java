package com.example.jetbrainsbackenddeveloperaccountservice.controller;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/*
    GET api/empl/payment gives access to the employee's payrolls;
    POST api/acct/payments uploads payrolls;
    PUT api/acct/payments updates payment information.
*/
@RestController
public class BusinessController {

    private final UserService userService;
    private final UserMapper userMapper;

    public BusinessController(@Autowired UserService userService,
                              @Autowired UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/api/empl/payment")
    public ResponseEntity<UserRegistrationDto> getPayment(Principal user) {
        return  user != null ? new ResponseEntity<>(
                new UserRegistrationDto(this.userService.findUserByEmail(user.getName())),
                HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
