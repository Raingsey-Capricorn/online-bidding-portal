package com.system.bidding.infrastructure.config.security.service;

import com.system.bidding.infrastructure.config.security.response.SSUserResponse;
import com.system.bidding.infrastructure.mapstruct.UserMapper;
import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import com.system.bidding.ports.outgoing.UserRegistrationService;
import com.system.bidding.ports.outgoing.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsService
        implements UserRegistrationService<SSUserResponse> {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * @param signUpParam : signUp parameter
     * @return UserModelResponse instance
     * @see SignUpParam#SignUpParam(String, String, String, String, Optional, Optional, Optional)
     */
    public SSUserResponse signUp(final HttpServletRequest request,
                                 final SignUpParam signUpParam) {

        final var savedUser = userService.save(userMapper.from(signUpParam, passwordEncoder));
        final var session = request.getSession(true);
        final var authenticatedUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signUpParam.email(),
                        signUpParam.password()));
        final var context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticatedUser);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context);
        return SSUserResponse
                .builder()
                .model(savedUser)
                .build();
    }

    /**
     * @param signInParam : signIn parameter
     * @return UserModelResponse instance
     * @see SignUpParam#SignUpParam(String, String, String, String, Optional, Optional, Optional)
     */
    public SSUserResponse signIn(final HttpServletRequest request,
                                 final SignInParam signInParam) throws UsernameNotFoundException {

        final var session = request.getSession(true);
        final var authenticatedUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInParam.email(),
                        signInParam.password()));
        final var context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticatedUser);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context);

        return SSUserResponse.builder()
                .model(userService.findUserByUserName(signInParam.email()))
                .build();
    }
}