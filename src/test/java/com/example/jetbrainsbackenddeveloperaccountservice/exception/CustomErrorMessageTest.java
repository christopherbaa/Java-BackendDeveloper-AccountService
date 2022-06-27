package com.example.jetbrainsbackenddeveloperaccountservice.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomErrorMessageTest {

    @Test
    void buildShouldReturnMessageWithFiveFields(){
        // given
        CustomErrorMessage exceptedMessage = new CustomErrorMessage(LocalDateTime.MIN,
                400,
                "someError",
                "someMessage",
                "/some/path");

        // when
        CustomErrorMessage actualMessage =  new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.MIN)
                .setStatus(400)
                .setError("someError")
                .setMessage("someMessage")
                .setPath("/some/path")
                .build();

        assertEquals(exceptedMessage, actualMessage);
    }
}