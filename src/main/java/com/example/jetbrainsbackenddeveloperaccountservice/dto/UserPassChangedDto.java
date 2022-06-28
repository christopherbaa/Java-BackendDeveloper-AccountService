package com.example.jetbrainsbackenddeveloperaccountservice.dto;

import com.example.jetbrainsbackenddeveloperaccountservice.model.User;

public class UserPassChangedDto {

    private String email;
    private String status;

    public UserPassChangedDto() {
    }

    public UserPassChangedDto(User user) {
        this.email = user.getEmail();
        this.status = "The password has been updated successfully";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
