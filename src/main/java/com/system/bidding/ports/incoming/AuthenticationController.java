package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import jakarta.validation.Valid;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface AuthenticationController<T> {

    /**
     * @param request : up-signing request
     * @return Signup response
     */
    T signUp(@Valid SignUpParam request);

    /**
     * @param request : in-singing request
     * @return Sign in response
     */
    T signIn(@Valid SignInParam request);
}
