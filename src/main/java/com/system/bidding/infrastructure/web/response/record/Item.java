package com.system.bidding.infrastructure.web.response.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param id:              id's param
 * @param name:            name's param
 * @param description:     description's param
 * @param clientId:        clientId's param
 * @param availability:    availability's param
 * @param price:           price's param
 * @param minBiddingPrice: minBiddingPrice's param
 * @param maxBiddingPrice: maxBiddingPrice's param
 * @param entryDate:       entryDate's param
 * @param expiryDate:      expiryDate's param
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
