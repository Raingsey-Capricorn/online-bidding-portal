package com.system.bidding.infrastructure.config.security.response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.bidding.domain.business.UserEntityModel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Builder
@RequiredArgsConstructor
public class SSUserResponse implements AuthenticatedPrincipal {

    private final UserEntityModel model;

    /**
     * @return
     */
    @Override
    public String getName() {
        return model.getUser().getEmail();
    }

    /**
     * @return
     */
    public Map<String, Object> getAttributes() {
        model.getUser().setPassword(null);
        return new ObjectMapper().convertValue(model, new TypeReference<>() {});
    }

    /**
     * @return
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return model.getAuthorities();
    }
}
