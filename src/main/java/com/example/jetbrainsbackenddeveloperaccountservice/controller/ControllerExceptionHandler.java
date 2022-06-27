package com.example.jetbrainsbackenddeveloperaccountservice.controller;

import com.example.jetbrainsbackenddeveloperaccountservice.exception.CustomErrorMessage;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.EmailExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<CustomErrorMessage> handleEmailExists(
            EmailExistsException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage("User exist!")
                .setPath(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setPath(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
