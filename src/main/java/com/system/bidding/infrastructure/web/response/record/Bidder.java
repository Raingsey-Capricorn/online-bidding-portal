package com.system.bidding.infrastructure.web.response.record;

import com.system.bidding.infrastructure.utilities.BidderType;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public record Bidder(
        Long id,
        String firstName,
        String lastName,
        String userName,
        BidderType type
) {
}