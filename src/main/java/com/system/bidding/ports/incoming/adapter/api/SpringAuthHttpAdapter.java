package com.system.bidding.ports.incoming.adapter.api;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.config.security.response.SSUserResponse;
import com.system.bidding.infrastructure.config.security.service.UserDetailsService;
import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import com.system.bidding.ports.incoming.AuthenticationRestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 10/10/23
 * Project : com.digital.system
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(URLEndpoints.AUTH_API_URL)
public class SpringAuthHttpAdapter
        implements AuthenticationRestController<ResponseEntity<SSUserResponse>> {

    private final UserDetailsService service;

    /**
     * @param signUpParam : request's parameter
     * @param response    : response's parameter
     * @return
     */
    @Override
    @SneakyThrows
    @PostMapping(value = URLEndpoints.API_SIGN_UP_URL,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SSUserResponse> signUp(
            final SignUpParam signUpParam,
            final HttpServletRequest request,
            final HttpServletResponse response) {

        log.info(">>>> Request signing up a new user: {}", signUpParam.email());
        return ResponseEntity.ok(service.signUp(request, signUpParam));
    }

    /**
     * @param signInParam : request's parameter
     * @param response    : response's parameter
     * @return
     */
    @Override
    @SneakyThrows
    @PostMapping(value = URLEndpoints.API_SIGN_IN_URL,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SSUserResponse> signIn(
            final SignInParam signInParam,
            final HttpServletRequest request,
            final HttpServletResponse response) {

        log.info(">>>> Request signing in existing user: {}", signInParam.email());
        return ResponseEntity.ok(service.signIn(request, signInParam));
    }
}
