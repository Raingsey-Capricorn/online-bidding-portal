package com.system.bidding.infrastructure.web.response.record;

import com.system.bidding.infrastructure.utilities.BidderType;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param id        : id's param
 * @param firstName : firstName's param
 * @param lastName  : lastName's param
 * @param userName  : userName's param
 * @param type      : type's param
 */
public record Bidder(
        Long id,
        String firstName,
        String lastName,
        String userName,
        BidderType type
) {
}