package com.system.bidding.ports.outgoing;

import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface BiddingService {

    /**
     * @param pageable : page request
     * @return Page of data
     */
    PagedListHolder<Item> getItemList(final Pageable pageable);

    /**
     * @param clientId : clientId's parameter
     * @param pageable : pageable's parameter
     * @return Page of data
     */
    PagedListHolder<Item> getItemList(
            final Long clientId,
            final Pageable pageable);

    /**
     * @param bidderId     :Bidder's ID
     * @param requestParam : page request
     * @return Page of data
     */
    PagedListHolder<BiddingHistory> getBiddingHistory(
            final Long bidderId,
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
