package com.system.bidding.infrastructure.config.security.response;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Map;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@RequiredArgsConstructor
public class OidCUserResponse implements OidcUser {

    private final OidcUser oAuth2User;

    /**
     * @return Map of claims
     */
    @Override
    public Map<String, Object> getClaims() {
        return oAuth2User.getClaims();
    }

    /**
     * @return OidcUserInfo information
     */
    @Override
    public OidcUserInfo getUserInfo() {
        return oAuth2User.getUserInfo();
    }

    /**
     * @return OidcIdToken's token
     */
    @Override
    public OidcIdToken getIdToken() {
        return oAuth2User.getIdToken();
    }

    /**
     * @return Map of attribute return from Authorization server
     */
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    /**
     * @return List of authorities return from Authorization server
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    /**
     * @return oAuth2 instance's name
     */
    @Override
    public String getName() {
        return oAuth2User.getName();
    }
}
