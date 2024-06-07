package com.system.bidding.infrastructure.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public abstract class PageRequestCreator {

    /**
     * @param page : current page
     * @param size : current records to fetch
     * @return PageRequest object
     */
    public static PageRequest createPageRequest(int page, int size) {
        return PageRequest.of(
                page,
                size,
                Sort.unsorted()
        );
    }
}
