package com.system.bidding.infrastructure.web.response.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public record Item(
        Long id,
        String name,
        String description,
        String clientId,
        Boolean availability,
        Double price,
        Double minBiddingPrice,
        Double maxBiddingPrice,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date entryDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date expiryDate
) {
}
