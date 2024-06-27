package com.system.bidding.infrastructure.config.security.handler;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.stereotype.Component;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Component
public class ExceptionCustomizingHandler implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {

    /**
     * @param configurer the input argument
     */
    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
        configurer.authenticationEntryPoint((request, response, authException) -> {
            response.sendRedirect(URLEndpoints.BASE_VIEW_LOGIN_URL);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        });
    }
}
