package com.example.jetbrainsbackenddeveloperaccountservice.dto;

import java.util.Objects;

public class PaymentResponseDto {

    String name;

    String lastname;
    String period;
    String salary;

    public PaymentResponseDto(String name, String lastname, String period, String salary) {
        this.name = name;
        this.lastname = lastname;
        this.period = period;
        this.salary = salary;
    }

    public PaymentResponseDto() {
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentResponseDto that = (PaymentResponseDto) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(lastname, that.lastname)) return false;
        if (!Objects.equals(period, that.period)) return false;
        return Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaymentResponseDto{" +
                "name='" + name + '\'' +
                ", lastName='" + lastname + '\'' +
                ", period='" + period + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
