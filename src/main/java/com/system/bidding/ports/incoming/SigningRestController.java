package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface SigningRestController<T> {

    /**
     * @param signUpParam : up-signing request
     * @param request     : http-servlet-request
     * @param response    : http-servlet-response
     * @return Authentication Response's instance
     * @see SignUpParam#SignUpParam(String, String, String, String, Optional, Optional, Optional)
     */
    T signUp(final SignUpParam signUpParam,
             final HttpServletRequest request,
             final HttpServletResponse response);

    /**
     * @param signInParam : in-signing request
     * @param request     : http-servlet-request
     * @param response    : http-servlet-response
     * @return Authentication Response's instance
     * @see SignInParam#SignInParam(String, String)
     */
    T signIn(final SignInParam signInParam,
             final HttpServletRequest request,
             final HttpServletResponse response);

    /**
     * @param request  : http-servlet-request
     * @param response : http-servlet-response
     * @return Authentication Response's instance
     */
    T signOut(
            final HttpServletRequest request,
            final HttpServletResponse response);
}
