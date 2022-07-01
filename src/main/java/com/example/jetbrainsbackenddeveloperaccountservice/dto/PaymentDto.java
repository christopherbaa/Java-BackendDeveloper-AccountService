package com.example.jetbrainsbackenddeveloperaccountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PaymentDto {


    @JsonProperty("employee")
    @NotBlank(message = "Employee is required!")
    private String email;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-(\\d{4})$", message = "Period must be mm-yyyy")
    private String period;

    @Min(value = 0, message = "Salary must be positive!")
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
