package com.system.bidding.infrastructure.config.security.service;

import com.system.bidding.infrastructure.config.security.response.OAuth2UserResponse;
import com.system.bidding.infrastructure.mapstruct.UserMapper;
import com.system.bidding.ports.outgoing.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserMapper userMapper;
    private final UserService userService;

    /**
     * @param userRequest : userRequest
     * @return OAuth2User instance (GitHub,...)
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        final var user = (OAuth2User) super.loadUser(userRequest);
        log.info("OAuth2UserService: user = {}", user);
        final var savedUser = userService.save(userMapper.from(user));
        final var context = SecurityContextHolder.createEmptyContext();
        final var authToken = new UsernamePasswordAuthenticationToken(
                savedUser.getUser(),
                savedUser.getEmail(),
                savedUser.getAuthorities()
        );
        authToken.setDetails(savedUser);
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
        return new OAuth2UserResponse(user);
    }
}
