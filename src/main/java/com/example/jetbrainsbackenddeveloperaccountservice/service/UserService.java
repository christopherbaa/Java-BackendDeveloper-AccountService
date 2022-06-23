package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;

public interface UserService {
    boolean registerUser(User user);
    UserMapper getUserMapper();
}
