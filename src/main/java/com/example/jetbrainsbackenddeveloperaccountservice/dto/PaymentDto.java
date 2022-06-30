package com.example.jetbrainsbackenddeveloperaccountservice.dto;

import com.example.jetbrainsbackenddeveloperaccountservice.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentDto {


    private String email;
    private String period;
    private Long salary;

    public PaymentDto() {
    }

    public PaymentDto(String email, String period, Long salary) {
        this.email = email;
        this.period = period;
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
