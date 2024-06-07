package com.system.bidding.infrastructure.config.security.service;


import com.system.bidding.domain.business.UserEntityModel;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.config.security.AuthenticationServiceProvider;
import com.system.bidding.infrastructure.config.security.JWTService;
import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import com.system.bidding.infrastructure.web.response.JwtAuthenticationResponse;
import com.system.bidding.ports.outgoing.adapter.database.UserServiceDBAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServiceProvider<JwtAuthenticationResponse> {

    private final AuthenticationManager authenticationManager;
    private final UserServiceDBAdapter userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    /**
     * @param request : SignUpRequest
     * @return JwtAuthenticationResponse's instance
     * @see SignUpParam#SignUpParam(String, String, String, String, SecurityConstant.AuthorizationRole)
     */
    @Override
    public JwtAuthenticationResponse signUp(SignUpParam request) {

        var user = new UserEntityModel()
                .setFirstName(request.firstName())
                .setLastName(request.lastName())
                .setEmail(request.email())
                .setPassword(passwordEncoder.encode(request.password()))
                .setRole(SecurityConstant.AuthorizationRole.valueOf(request.role().name()));

        final var response = new AtomicReference<JwtAuthenticationResponse>();
        Optional.ofNullable(userService.save(user))
                .ifPresent(entityVO -> response.set(JwtAuthenticationResponse
                        .builder()
                        .token(jwtService.generateToken(user))
                        .build()
                ));
        return response.get();
    }

    /**
     * @param request : SignInRequest
     * @return JwtAuthenticationResponse's instance
     * @see SignInParam#SignInParam(String, String)
     */
    @Override
    public JwtAuthenticationResponse signIn(SignInParam request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password())
        );

        final var response = new AtomicReference<JwtAuthenticationResponse>();
        Optional.ofNullable(userService.userDetailsService()
                        .loadUserByUsername(request.email()))
                .ifPresent(entityVO -> response.set(JwtAuthenticationResponse
                        .builder()
                        .token(jwtService.generateToken(entityVO))
                        .build()
                ));
        return response.get();
    }
}
