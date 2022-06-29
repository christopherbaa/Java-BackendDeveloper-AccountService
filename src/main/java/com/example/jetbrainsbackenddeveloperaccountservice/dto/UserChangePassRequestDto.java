package com.example.jetbrainsbackenddeveloperaccountservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserChangePassRequestDto {

    // Mappen von JSON zu Java um Name zu behalten @Property()
    @NotBlank(message = "Password is required!")
    @Size(min = 12, message = "Password length must be 12 chars minimum!")
    private String new_password;

    public UserChangePassRequestDto() {
    }

    public UserChangePassRequestDto(String new_password) {
        this.new_password = new_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
