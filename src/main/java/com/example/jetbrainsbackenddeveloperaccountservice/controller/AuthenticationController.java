package com.example.jetbrainsbackenddeveloperaccountservice.controller;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import com.example.jetbrainsbackenddeveloperaccountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationDto> registerUser(@Valid @RequestBody User user) {
        return this.userService.registerUser(user) ?
                new ResponseEntity<>(this.userService.getUserMapper().toUserRegistrationDto(user), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
