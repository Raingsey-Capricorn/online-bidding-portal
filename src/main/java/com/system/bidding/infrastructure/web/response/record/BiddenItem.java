package com.system.bidding.infrastructure.web.response.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param item             : item's param
 * @param bidder           : bidder's param
 * @param isWon            : isWon's param
 * @param biddingDate      : biddingDate's param
 * @param maxBiddingPrice  : maxBiddingPrice's param
 * @param lastBiddingPrice : lastBiddingPrice's param
 */
public record BiddenItem(
        Item item,
        Bidder bidder,
        Boolean isWon,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date biddingDate,
        Double maxBiddingPrice,
        Double lastBiddingPrice
) {
}
