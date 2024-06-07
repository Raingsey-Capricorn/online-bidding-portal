package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.utilities.PageRequestCreator;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public record ItemParam(
        @Getter
        @Min(value = 0) Optional<Integer> page,
        @Getter
        @Min(value = 1) Optional<Integer> size,
        @Getter
        Optional<String> sortField,
        @Getter
        Optional<String> sortDirection,
        Optional<Boolean> isWon
) {
    /**
     * @return request parameter
     */
    public PageRequest createPageRequest() {
        return PageRequestCreator.createPageRequest(
                page.orElse(1),
                size.orElse(5));
    }

}

