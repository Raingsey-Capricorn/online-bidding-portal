package com.system.bidding.infrastructure.web.response.record;

import lombok.Builder;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param bidderId        : bidderId's argument
 * @param sessionId       : sessionId's argument
 * @param biddingTime     : biddingTime's argument
 * @param defaultDuration : defaultDuration's argument
 * @param remark          : remark's argument
 */
@Builder
public record BiddingSession(
        Long bidderId,
        String sessionId,
        String biddingTime,
        Long defaultDuration,
        String remark) {
}
