package com.example.jetbrainsbackenddeveloperaccountservice.dto;

public class StatusMessage {

    private String status;

    public StatusMessage(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
