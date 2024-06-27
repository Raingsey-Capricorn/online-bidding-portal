package com.system.bidding.ports.outgoing;

import com.system.bidding.infrastructure.database.entity.ItemEntity;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemDetails;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 * This service is for reading resource only
 */
public interface ItemManagementService {

    /**
     * @param pageable : page request
     * @return Page of data
     */
    PagedListHolder<Item> getItemList(final Pageable pageable);

    /**
     * @param pageable : page request
     * @return Page of data
     */
    PagedListHolder<Item> getBiddingItemBoardList(
            final Pageable pageable);

    /**
     * @param userId   : security principal
     * @param pageable : pageable's parameter
     * @return Page of data
     */
    PagedListHolder<Item> getItemList(
            final Long userId,
            final Pageable pageable);

    /**
     * @param pageable : pageable's parameter
     * @return list of announcement
     */
    PagedListHolder<Announcement> getAnnouncements(
            final Pageable pageable);

    /**
     * @param pageable : pageable's parameter
     * @return PagedListHolder<BiddenItem>
     */
    PagedListHolder<BiddenItem> getItemBiddingHistory(
            final Long userId,
            final Pageable pageable);

    /**
     * @param itemId : Item ID
     * @return ItemDetails
     * @see ItemDetails#ItemDetails(Item, List, Date, Double, Double)
     */
    ItemDetails getItemDetails(final Long itemId);

    /**
     * @param item : item entity
     * @return Item
     */
    Item saveItem(final ItemEntity item);

}
