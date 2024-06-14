package com.system.bidding.infrastructure.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Component
public class FailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param request   : request's parameter
     * @param response  : response's parameter
     * @param exception : exception's parameter
     * @throws IOException : the exception which was thrown to reject the authentication request.
     */
    @Override
    public void onAuthenticationFailure(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final org.springframework.security.core.AuthenticationException exception)
            throws IOException {

        log.error("!!! Authentication error : {}", exception.getMessage());
        request.getSession().setAttribute("error.message", exception.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", Calendar.getInstance().getTime());
        data.put("exception", exception.getMessage());
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}
