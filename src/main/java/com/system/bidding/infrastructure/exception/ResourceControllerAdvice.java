package com.system.bidding.infrastructure.exception;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.HashMap;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@RestControllerAdvice
public class ResourceControllerAdvice {
    /**
     * @param notValidException : notValidException
     * @param request           : request
     * @return ResponseEntity<Object>
     */
    @SneakyThrows
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleInvalidMethodArguments(
            final MethodArgumentNotValidException notValidException,
            final WebRequest request) {

        log.error(notValidException.getMessage());
        final var errorResponseMap = new HashMap<String, String>();
        notValidException
                .getFieldErrors()
                .forEach(objectError -> errorResponseMap.put(
                        objectError.getField(),
                        objectError.getDefaultMessage())
                );

        final var uri = new URI(((ServletWebRequest) request).getRequest().getRequestURI());
        final var errorBody = notValidException.getBody();
        errorBody.setProperty("details", errorResponseMap);
        errorBody.setTitle(notValidException.getMessage().substring(0, 10));
        errorBody.setStatus(HttpStatus.BAD_REQUEST.value());
        errorBody.setStatus(HttpStatus.BAD_REQUEST);
        errorBody.setInstance(uri);
        errorBody.setType(uri);
        return ResponseEntity.badRequest().body(errorBody);
    }

    /**
     * @param credentialsException : Credential exception
     * @param request              : request
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentials(
            final BadCredentialsException credentialsException,
            final WebRequest request
    ) {
        log.error(credentialsException.getMessage());
        return ResponseEntity.badRequest().body(credentialsException.getMessage());
    }

}
