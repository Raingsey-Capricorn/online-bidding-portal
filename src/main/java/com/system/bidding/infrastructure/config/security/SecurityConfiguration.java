package com.system.bidding.infrastructure.config.security;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.config.security.service.AuthenticationFailureHandlerService;
import com.system.bidding.ports.outgoing.adapter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String AUTHORIZATION_URL = URLEndpoints.AUTH_API_URL + "/**";
    public static final String AUTHORIZED_VIEW_URL = URLEndpoints.BASE_VIEW_URL + "/**";
    public static final String ADMIN_AUTHORIZED_URL = URLEndpoints.BASE_API_ADMIN_URL + "/**";
    public static final String RESOURCES_URL = "/resource/**";
    private static final String[] OPEN_APIS_V3 = {
            "configuration/**",
            "/api/auth/**",
            "/v3/api-docs/**",
            "/swagger*/**",
            "/swagger-ui/**",
            "/webjars/**"
    };

    private final UserService userService;
//    private final JWTAuthenticationFilter filter;

    /**
     * @param httpSecurity : spring argument http-security param
     * @return SecurityFilterChain instance
     */
    @Bean
    public SecurityFilterChain chain(
            final HttpSecurity httpSecurity
    ) throws Exception {

        httpSecurity
                .csrf(configurer -> configurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(matcherRegistry ->
                        matcherRegistry
                                .requestMatchers(ADMIN_AUTHORIZED_URL).hasAnyAuthority(
                                        SecurityConstant.Authority.ROLE_ADMIN,
                                        SecurityConstant.Authority.ROLE_USER)
                                .requestMatchers(AUTHORIZATION_URL).permitAll()
                                .requestMatchers(OPEN_APIS_V3).permitAll()
                                .requestMatchers(LOGIN_URL).permitAll()
                                .requestMatchers(RESOURCES_URL).permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(configurer -> configurer.loginPage(LOGIN_URL))
                .logout(configurer -> configurer.logoutSuccessUrl("/").permitAll())
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .oauth2Login(configurer -> configurer.failureHandler((request, response, exception) -> {
                            request.getSession().setAttribute("error.message", exception.getMessage());
                            new AuthenticationFailureHandlerService().onAuthenticationFailure(request, response, exception);
                        })
                )
        ;
        return httpSecurity.build();
    }

    /**
     * @return AuthenticationProvider instance
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userService.userDetailsService());
        return authProvider;
    }

    /**
     * @param config : authentication param
     * @return AuthenticationManager instance
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Ignore static resources (CSS, JS, IMAGES)
     *
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer customizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/css/**")
                .requestMatchers("/img/**")
                ;
    }
}