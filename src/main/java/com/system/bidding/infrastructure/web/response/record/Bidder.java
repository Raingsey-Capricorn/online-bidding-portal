package com.system.bidding.infrastructure.web.response.record;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.utilities.BidderType;
import lombok.Builder;

import java.util.Date;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param userId                : id's argument
 * @param loginTime             : loginTime's argument
 * @param sessionStartDate      : sessionStartDate's argument
 * @param credential            : credential's argument
 * @param userName              : userName's argument
 * @param authorizationServer   : authorizationServer's argument
 * @param sessionDuration       : sessionDuration's argument
 * @param type                  : type's argument
 * @param authorizationProvider : authorizationProvider's argument
 */
@Builder
public record Bidder(
        Long userId,
        Date loginTime,
        Date sessionStartDate,
        String credential,
        String userName,
        String authorizationServer,
        Integer sessionDuration,
        BidderType type,
        SecurityConstant.AuthorizationProvider authorizationProvider) {
}