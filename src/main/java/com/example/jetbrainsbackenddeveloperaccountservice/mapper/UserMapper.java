package com.example.jetbrainsbackenddeveloperaccountservice.mapper;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import com.example.jetbrainsbackenddeveloperaccountservice.model.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserRegistrationDto toUserRegistrationDto(User user) {
        return new UserRegistrationDto(user);
    }

    public User toUserWithEncryptedPw(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setRole(UserRole.ROLE_USER);
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return newUser;
    }
}
