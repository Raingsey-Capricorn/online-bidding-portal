package com.system.bidding.infrastructure.config.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstant {

    /**
     *
     */
    public enum Authority {
        /**
         * Role as user
         */
        USER,
        /**
         * Role as admin
         */
        ADMIN,
        /**
         * Role as system
         */
        SYSTEM,
        /**
         * Role as third-party (OpenID Connect, OAuth2,...)
         */
        ANONYMOUS;

        /**
         * @return list of enumerated roles
         */
        public static List<Authority> list() {
            return Arrays.asList(USER, ADMIN, SYSTEM, ANONYMOUS);
        }
    }

    public enum AuthorizationProvider {
        /**
         * Spring Security User-Password
         */
        SSUP,
        /**
         * OpenID connect
         */
        GIT_HUB,
        /**
         * OAuth2
         */
        GOOGLE;

        /**
         * @return list of enumerated roles
         */
        public static List<AuthorizationProvider> list() {
            return Arrays.asList(SSUP, GIT_HUB, GOOGLE);
        }
    }


}
