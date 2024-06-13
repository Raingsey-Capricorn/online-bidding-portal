package com.system.bidding.ports.outgoing;

import com.system.bidding.infrastructure.web.request.SignInParam;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;


public interface UserRegistrationService<T> {

    /**
     * @param request     : http servlet request
     * @param signUpParam : sing-in parameter
     * @return generic type provided by derived classes
     * @see SignUpParam#SignUpParam(String, String, String, String, Optional, Optional, Optional)
     */
    T signUp(final HttpServletRequest request,
             final SignUpParam signUpParam);

    /**
     * @param request     : http servlet request
     * @param signInParam : sing-in parameter
     * @return generic type provided by derived classes
     * @see SignUpParam#SignUpParam(String, String, String, String, Optional, Optional, Optional)
     */
    T signIn(final HttpServletRequest request,
             final SignInParam signInParam);
}
