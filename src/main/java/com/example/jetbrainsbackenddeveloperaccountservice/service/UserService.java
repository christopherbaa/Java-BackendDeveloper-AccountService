package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserChangePassRequestDto;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserPassChangedDto;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;

public interface UserService {
    public UserRegistrationDto registerUser(User user);
    public UserMapper getUserMapper();
    public User findUserByEmail(String email);
    public UserPassChangedDto changePassword(User user, String newPass);
}
