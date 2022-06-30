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
    private User user;
    private LocalDateTime period;
    private Long salary;

    public Payment() {
    }

    public Payment(Long id, User user, LocalDateTime period, Long salary) {
        this.id = id;
        this.user = user;
        this.period = period;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEmployee() {
        return user;
    }

    public void setEmployee(User user) {
        this.user = user;
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
