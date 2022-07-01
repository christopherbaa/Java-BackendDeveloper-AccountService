package com.example.jetbrainsbackenddeveloperaccountservice.controller;

import com.example.jetbrainsbackenddeveloperaccountservice.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @ExceptionHandler(PasswordMatchesException.class)
    public ResponseEntity<CustomErrorMessage> handlePasswordMatches(
            PasswordMatchesException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage("The passwords must be different!")
                .setPath(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordIsCompromisedException.class)
    public ResponseEntity<CustomErrorMessage> handlePasswordIsCompromised(
            PasswordIsCompromisedException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage("The password is in the hacker's database!")
                .setPath(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<CustomErrorMessage> handleUserDoesNotExist(
            UserDoesNotExistException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage(e.getMessage() + " not found!")
                .setPath(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDistinctPeriodsException.class)
    public ResponseEntity<CustomErrorMessage> handleNoDistinctPeriods(
            NoDistinctPeriodsException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage(e.getMessage())
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
                .setMessage(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage())
                .setPath(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
