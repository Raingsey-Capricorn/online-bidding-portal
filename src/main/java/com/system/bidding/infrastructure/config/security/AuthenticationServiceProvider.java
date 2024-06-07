package com.system.bidding.infrastructure.config.security;

import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;


public interface AuthenticationServiceProvider<T> {

    /**
     * @param request : SignUpRequest
     * @return JwtAuthenticationResponse's instance
     * @see SignUpParam#SignUpParam(String, String, String, String, com.system.bidding.infrastructure.config.constants.SecurityConstant.AuthorizationRole)
     */
    T signUp(SignUpParam request);

    /**
     * @param request : SignInRequest
     * @return JwtAuthenticationResponse's instance
     * @see SignInParam#SignInParam(String, String)
     */
    T signIn(SignInParam request);
}
