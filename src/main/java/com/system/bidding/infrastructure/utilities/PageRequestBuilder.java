package com.system.bidding.infrastructure.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public abstract class PageRequestBuilder {

    /**
     * @param page : current page
     * @param size : current records to fetch
     * @return PageRequest object
     */
    public static PageRequest build(int page, int size) {
        return PageRequest.of(
                page,
                size,
                Sort.unsorted()
        );
    }
}
