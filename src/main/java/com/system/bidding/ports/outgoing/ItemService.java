package com.system.bidding.ports.outgoing;

import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 * This service is for reading resource only
 */
public interface ItemService {

    /**
     * @param pageable : page request
     * @return Page of data
     */
    PagedListHolder<Item> getItemList(final Pageable pageable);

    /**
     * @param userId   : security principal
     * @param pageable : pageable's parameter
     * @return Page of data
     */
    PagedListHolder<Item> getItemList(
            final Long userId,
            final Pageable pageable);

    /**
     * @param userId       : security principal
     * @param requestParam : page request
     * @return Page of data
     */
    PagedListHolder<BiddingHistory> getBiddingHistory(
            final Long userId,
            final ItemParam requestParam);

    /**
     * @param itemId : Item's ID
     * @return Item detials
     */
    ItemBiddingDetails getItemDetails(final Long itemId);

    /**
     * @param pageable : pageable's parameter
     * @return list of announcement
     */
    PagedListHolder<Announcement> getAnnouncements(final Pageable pageable);

}
