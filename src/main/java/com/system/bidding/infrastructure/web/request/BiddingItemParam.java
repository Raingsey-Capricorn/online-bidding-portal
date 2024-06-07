package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.web.response.record.Bidder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param id    : Item's ID
 * @param name  : item's name
 * @param price : item's price
 */
public record BiddingItemParam(
        @NotNull(message = "Item's Id is required")
        Long id,
        @NotEmpty(message = "Item name must be presented")
        String name,
        @NotNull(message = "Price is missing")
        @Min(value = 0, message = "Price can be started with 0 and required")
        Double price,
        @NotNull(message = "Bidder's Id is missing")
        Optional<Bidder> bidder
) {
}
