package com.system.bidding.infrastructure.config.security;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.config.filter.RequestFilter;
import com.system.bidding.infrastructure.config.security.handler.ExceptionCustomizingHandler;
import com.system.bidding.infrastructure.config.security.handler.FailureHandler;
import com.system.bidding.infrastructure.config.security.handler.SuccessHandler;
import com.system.bidding.infrastructure.config.security.service.OAuth2UserService;
import com.system.bidding.infrastructure.config.security.service.OidCUserService;
import com.system.bidding.ports.outgoing.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    public final static String DEFAULTED_SUCCESS_URL = URLEndpoints.BASE_VIEW_URL + URLEndpoints.DASHBOARD_URL;
    private final ExceptionCustomizingHandler exceptionCustomizingHandler;
    private final OAuth2UserService oAuth2UserService;
    private final RequestFilter authenticationFilter;
    private final UserModelService userModelService;
    private final OidCUserService oidcUserService;
    private final SuccessHandler successHandler;
    private final FailureHandler failureHandler;

    /**
     * @param httpSecurity : spring argument http-security param
     * @return SecurityFilterChain instance
     */
    @Bean
    public SecurityFilterChain chain(final HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .csrf(configurer -> configurer
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                URLEndpoints.BASE_VIEW_LOGIN_URL,
                                URLEndpoints.OAUTH2_API_URL + "/**",
                                URLEndpoints.AUTH_API_URL + "/**",
                                URLEndpoints.BASE_API_URL + "/**"
                        ))
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(HttpMethod.POST, URLEndpoints.OAUTH2_API_URL + "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, URLEndpoints.AUTH_API_URL + "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, URLEndpoints.BASE_VIEW_LOGIN_URL).permitAll()
                        .requestMatchers(HttpMethod.GET, URLEndpoints.EXCLUDE_URI).permitAll()
                        .requestMatchers(URLEndpoints.BASE_API_URL + "/**").authenticated()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(exceptionCustomizingHandler)
                .formLogin(configurer -> configurer
                        .loginPage(URLEndpoints.BASE_VIEW_LOGIN_URL)
                        .defaultSuccessUrl(DEFAULTED_SUCCESS_URL))
                .oauth2Login(configurer -> configurer
                        .userInfoEndpoint(config -> config
                                .oidcUserService(oidcUserService)
                                .userService(oAuth2UserService))
                        .successHandler(successHandler)
                        .failureHandler(failureHandler))
                .build();
    }

    /**
     * @param config : authentication param
     * @return AuthenticationManager instance
     * @throws Exception : exception thrown by Authentication server
     */
    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * @return AuthenticationProvider instance
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(userModelService);
        return auth;
    }

    /**
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}