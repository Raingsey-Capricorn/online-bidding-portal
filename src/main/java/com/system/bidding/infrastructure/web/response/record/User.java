package com.system.bidding.infrastructure.web.response.record;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import lombok.Builder;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Builder
public record User(
        String userName,
        String email,
        String password,
        SecurityConstant.AuthorizationRole role,
        SecurityConstant.AuthorizationProvider provider) {
}
