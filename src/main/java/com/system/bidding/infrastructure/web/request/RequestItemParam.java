package com.system.bidding.infrastructure.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param name         : item's name
 * @param description  : item's description
 * @param availability : item's availability
 * @param price        : item's price
 */
public record RequestItemParam(
        @NotEmpty(message = "Item name must be presented")
        String name,
        @NotEmpty(message = "Item description is required")
        String description,
        @NotNull(message = "Availability for bidding item is required (true or false)")
        Boolean availability,
        @NotNull(message = "Price is missing")
        @Min(value = 0, message = "Price can be started with 0 and required")
        Double price
) {
}
