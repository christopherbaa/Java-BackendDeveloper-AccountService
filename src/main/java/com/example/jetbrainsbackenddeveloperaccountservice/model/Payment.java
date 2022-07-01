package com.example.jetbrainsbackenddeveloperaccountservice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(columnDefinition = "DATE")
    private LocalDate period;
    private Long salary;

    public Payment() {
    }

    public Payment(Long id, User user, LocalDate period, Long salary) {
        this.id = id;
        this.user = user;
        this.period = period;
        this.salary = salary;
    }

    public Payment(User user, LocalDate period, Long salary) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
