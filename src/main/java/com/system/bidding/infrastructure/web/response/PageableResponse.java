package com.system.bidding.infrastructure.web.response;

import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.infrastructure.web.response.record.Bidder;
import com.system.bidding.infrastructure.web.response.record.Item;
import org.springframework.beans.support.PagedListHolder;

import java.util.*;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public abstract class PageableResponse {

    public static final String TOTAL = "total";
    public static final String SIZE = "size";
    public static final String PAGE = "page";
    public static final String PREV = "prev";
    public static final String NEXT = "next";
    public static final String ITEMS = "items";
    public static final String DISPLAYS = "displays";
    public static final String HAS_NEXT = "hasNext";
    public static final String HAS_PREV = "hasPrev";
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_SIZE = 5;
    public static final List<Integer> DEFAULT_RECORD_LIMIT = List.of(5, 10, 20, 30);

    /**
     * @param listHolder : list of data
     * @return map with pagination attribute
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    public static HashMap<String, ?> pagingItems(
            final RequestPageableParam param,
            final PagedListHolder<Item> listHolder) {

        return buildPaginationMap(
                param.getSize().orElse(DEFAULT_SIZE),
                param.getPage().orElse(DEFAULT_PAGE),
                listHolder);
    }

    /**
     * @param pageableParam : pageableParam
     * @param listHolder    : list holder
     * @return HashMap<String, ?> map
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     * @see Item#Item(Long, String, Long, String, Boolean, Double, Double, Double, Date, Date)
     */
    public static HashMap<String, ?> pagingBiddingItems(
            final RequestPageableParam pageableParam,
            final PagedListHolder<Item> listHolder) {

        return buildPaginationMap(
                pageableParam.getSize().orElse(DEFAULT_SIZE),
                pageableParam.getPage().orElse(DEFAULT_PAGE),
                listHolder);
    }

    /**
     * @param listHolder : list of data
     * @return map with pagination attribute
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     * @see BiddenItem#BiddenItem(Item, Bidder, Boolean, Date, Double, Double)
     */
    public static HashMap<String, ?> pagingHistory(
            final ItemParam param,
            final PagedListHolder<BiddenItem> listHolder) {

        return buildPaginationMap(
                param.getSize().orElse(DEFAULT_SIZE),
                param.getPage().orElse(DEFAULT_PAGE),
                listHolder);
    }

    /**
     * @param listHolder : list of data
     * @return map with pagination attribute
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     * @see Announcement#Announcement(Long, Item, Bidder, Date, String, Integer, Double, Double, Double)
     */
    public static HashMap<String, ?> pagingAnnouncement(
            final RequestPageableParam param,
            final PagedListHolder<Announcement> listHolder) {

        return buildPaginationMap(
                param.getSize().orElse(DEFAULT_SIZE),
                param.getPage().orElse(DEFAULT_PAGE),
                listHolder);
    }

    /**
     * @param size       : parameter's size request
     * @param page       : parameter's page request
     * @param listHolder : parameter's listHolder data
     * @return Map of pagination
     */
    private static HashMap<String, Object> buildPaginationMap(
            final int size,
            final int page,
            PagedListHolder<?> listHolder) {

        return new HashMap<>() {{
            put(SIZE, size);
            put(PAGE, page);
            put(PREV, page - 1);
            put(NEXT, page + 1);
            put(ITEMS, Optional.of(listHolder.getPageList()).orElse(Collections.emptyList()));
            put(DISPLAYS, DEFAULT_RECORD_LIMIT);
            put(HAS_NEXT, !listHolder.isLastPage());
            put(HAS_PREV, !listHolder.isFirstPage());
            put(TOTAL, listHolder.getSource().size());
        }};
    }

}
