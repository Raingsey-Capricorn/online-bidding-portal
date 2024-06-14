package com.system.bidding.infrastructure.web.response.record;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import lombok.Builder;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param userName : userName's param
 * @param email    : email's param
 * @param password : password's param
 * @param role     : role's param
 * @param provider : provider's param
 */
@Builder
public record User(
        String userName,
        String email,
        String password,
        SecurityConstant.Authority role,
        SecurityConstant.AuthorizationProvider provider) {
}
