package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.web.response.record.Bidder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param id     : Item's ID
 * @param name   : item's name
 * @param price  : item's price
 * @param bidder : bidder's info
 */
public record BiddenItemParam(
        @NotNull(message = "Item's Id is required")
        Long id,
        String name,
        String description,
        @NotNull(message = "Price is missing")
        @Min(value = 0, message = "Price can be started with 0 and required")
        Double price,
        @NotNull(message = "Bidder's Id is missing")
        Optional<Bidder> bidder
) {
}
