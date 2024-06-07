package com.system.bidding.infrastructure.web.response.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */

public record ItemBiddingDetails(
        Item item,
        List<Bidder> bidders,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date biddingDate,
        Double maxBiddingPrice,
        Double lastBiddingPrice
) {
}
