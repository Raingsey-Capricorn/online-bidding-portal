package com.system.bidding.infrastructure.web.response.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param id              : id's parameter
 * @param item            : item's parameter
 * @param winner          : winner's parameter
 * @param biddingDate     : biddingDate's parameter
 * @param description     : description's parameter
 * @param bidderAttend    : bidder attended parameter
 * @param originalPrice   : originalPrice's parameter
 * @param minBiddingPrice : minBiddingPrice's parameter
 * @param maxBiddingPrice : maxBiddingPrice's parameter
 */
public record Announcement(
        Long id,
        Item item,
        Bidder winner,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date biddingDate,
        String description,
        Integer bidderAttend,
        Double originalPrice,
        Double minBiddingPrice,
        Double maxBiddingPrice
) {
}
