package com.system.bidding.infrastructure.web.response;

import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import org.springframework.beans.support.PagedListHolder;

import java.util.HashMap;
import java.util.List;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public abstract class PageableResponseModel {

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
     * @param listHolder : list of data
     * @return map with pagination attribute
     */
    public static HashMap<String, ?> pagingHistory(
            final ItemParam param,
            final PagedListHolder<BiddingHistory> listHolder) {

        return buildPaginationMap(
                param.getSize().orElse(DEFAULT_SIZE),
                param.getPage().orElse(DEFAULT_PAGE),
                listHolder);
    }

    /**
     * @param listHolder : list of data
     * @return map with pagination attribute
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
            put(ITEMS, listHolder.getPageList());
            put(DISPLAYS, DEFAULT_RECORD_LIMIT);
            put(HAS_NEXT, !listHolder.isLastPage());
            put(HAS_PREV, !listHolder.isFirstPage());
        }};
    }

}
