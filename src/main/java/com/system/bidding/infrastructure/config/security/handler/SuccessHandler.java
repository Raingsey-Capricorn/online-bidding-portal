package com.system.bidding.infrastructure.config.security.handler;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    /**
     * @param request        : request from Auth provider
     * @param response       : response for redirect URL
     * @param authentication : authentication info
     * @throws IOException the exception which was thrown to reject the authentication request.
     */
    @Override
    public void onAuthenticationSuccess(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication) throws IOException, ServletException {

        request.getSession().setAttribute("success.message", authentication.getName());
        log.info("Successfully authenticated user [{}] from provider [{}]",
                ((OAuth2User) authentication.getPrincipal()).getAttribute("name"),
                ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().toUpperCase()
        );
        response.sendRedirect(URLEndpoints.BASE_VIEW_URL + URLEndpoints.DASHBOARD_URL);
    }
}
