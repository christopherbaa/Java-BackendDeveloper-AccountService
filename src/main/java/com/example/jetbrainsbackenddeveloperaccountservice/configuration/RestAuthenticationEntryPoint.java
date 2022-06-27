package com.example.jetbrainsbackenddeveloperaccountservice.configuration;

import com.example.jetbrainsbackenddeveloperaccountservice.exception.CustomErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {



        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        objectMapper.setDateFormat(df);

        CustomErrorMessage body = new CustomErrorMessage.Builder()
                .setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setError(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .setMessage("")
                .setPath(request.getRequestURI().replace("uri=", ""))
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(objectMapper.writeValueAsString(body));
    }
}
