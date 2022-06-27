package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;

public interface UserService {
    UserRegistrationDto registerUser(User user);
    UserMapper getUserMapper();
    User findUserByEmail(String email);
}
