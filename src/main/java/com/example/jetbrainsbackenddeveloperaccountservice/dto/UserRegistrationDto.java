package com.example.jetbrainsbackenddeveloperaccountservice.dto;

import com.example.jetbrainsbackenddeveloperaccountservice.model.User;

public class UserRegistrationDto {
    private String name;
    private String lastname;
    private String email;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(User user) {
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
