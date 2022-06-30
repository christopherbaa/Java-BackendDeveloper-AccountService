package com.example.jetbrainsbackenddeveloperaccountservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "payments")
public class Payment {

    private final String DATE_FORMATTER = "mm-YYYY";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User employee;
    private LocalDateTime period;
    private Long salary;

    public Payment() {
    }

    public Payment(User employee, LocalDateTime period, Long salary) {
        this.employee = employee;
        this.period = period;
        this.salary = salary;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public LocalDateTime getPeriod() {
        return period;
    }

    public void setPeriod(LocalDateTime period) {
        this.period = period;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
