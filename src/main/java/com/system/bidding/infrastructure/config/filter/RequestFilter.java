package com.system.bidding.infrastructure.config.filter;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final CacheManager cacheManager;


    /**
     * @param request     : request with credential
     * @param response    : response for further process
     * @param filterChain : spring filter chain
     * @throws ServletException : exception related to Servlet
     * @throws IOException      : Input-Output exception
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().contains(URLEndpoints.BASE_VIEW_URL) ||
                request.getRequestURI().contains(URLEndpoints.BASE_API_URL)) {
            log.info("{}", request.getRequestURL());
        }
        filterChain.doFilter(request, response);
    }
}
