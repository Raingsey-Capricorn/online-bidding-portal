package com.system.bidding.infrastructure.config.security.service;

import com.system.bidding.infrastructure.config.security.response.OidCUserResponse;
import com.system.bidding.infrastructure.mapstruct.UserMapper;
import com.system.bidding.ports.outgoing.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OidCUserService extends OidcUserService {

    private final UserMapper userMapper;
    private final UserService userService;

    /**
     * @param userRequest : userRequest
     * @return OpenID Connect instance (Google)
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        final var user = super.loadUser(userRequest);
        log.info("OidcUserService: user = {}", user);
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
        return new OidCUserResponse(user);
    }
}
