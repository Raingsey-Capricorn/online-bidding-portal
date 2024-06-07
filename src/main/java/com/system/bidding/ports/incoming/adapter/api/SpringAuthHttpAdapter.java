package com.system.bidding.ports.incoming.adapter.api;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.config.security.service.AuthenticationService;
import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import com.system.bidding.infrastructure.web.response.JwtAuthenticationResponse;
import com.system.bidding.ports.incoming.AuthenticationController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        implements AuthenticationController<ResponseEntity<JwtAuthenticationResponse>> {

    private final AuthenticationService authenticationService;

    /**
     * @param request : up-signing request
     * @return
     */
    @Override
    @PostMapping(URLEndpoints.AUTH_API_SIGN_UP_URL)
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpParam request) {
        log.info(">>>> Request signing up a new user: {}", request.email());
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    /**
     * @param request : in-singing request
     * @return
     */
    @Override
    @PostMapping(URLEndpoints.AUTH_API_SIGN_IN_URL)
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInParam request) {
        log.info(">>>> Request signing in existing user: {}", request.email());
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
