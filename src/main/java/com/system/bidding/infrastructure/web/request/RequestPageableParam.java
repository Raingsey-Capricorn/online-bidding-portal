package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.utilities.PageRequestBuilder;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param page          : page's parameter
 * @param size          : size's parameter
 * @param sortField     : sortField's parameter
 * @param sortDirection : sortDirection's parameter
 */
public record RequestPageableParam(

        @Getter
        @Min(value = 0) Optional<Integer> page,
        @Getter
        @Min(value = 1) Optional<Integer> size,
        @Getter
        Optional<String> sortField,
        @Getter
        Optional<String> sortDirection
) {
    /**
     * @return request parameter
     */
    public PageRequest createPageRequest() {
        return PageRequestBuilder.build(
                page.orElse(1),
                size.orElse(5));
    }
}
