package com.system.bidding.ports.incoming.adapter.api;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import com.system.bidding.ports.incoming.AuthenticationController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(URLEndpoints.OAUTH2_API_URL)
public class SpringOAuth2HttpAdapter
        implements AuthenticationController<Map<String, Object>> {

    /**
     * @param request : up-signing request
     * @return
     */
    @Override
    @PostMapping(URLEndpoints.AUTH_API_SIGN_UP_URL)
    public Map<String, Object> signUp(SignUpParam request) {

        log.info(">>>> Request signing up a new user: {}", request.email());
        return null;
    }

    /**
     * @param request : in-singing request
     * @return
     */
    @Override
    @PostMapping(URLEndpoints.AUTH_API_SIGN_IN_URL)
    public Map<String, Object> signIn(SignInParam request) {

        log.info(">>>> Request signing in existing user: {}", request.email());
        return null;
    }

    /**
     * @param principal
     * @return
     */
    @GetMapping("/user")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {

        log.info(">>>> User: {}", principal);
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    /**
     * @param request
     * @return
     */
    @GetMapping("/error")
    public String getErrorInfo(HttpServletRequest request) {

        log.info(">>>> Request: {}", request.getRequestURI());
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }
}
