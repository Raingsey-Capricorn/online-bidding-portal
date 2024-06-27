package com.system.bidding.ports.outgoing;

import com.system.bidding.infrastructure.web.request.BiddenItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import org.springframework.beans.support.PagedListHolder;

import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface BiddingManagementService {

    /**
     * @param requestParam : item param
     * @return List<BiddingHistory> instance
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    PagedListHolder<BiddenItem> getItems(
            final ItemParam requestParam);

    /**
     * @param userId       : bidder's ID
     * @param requestParam : item param
     * @return List<BiddingHistory> instance
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     */
    PagedListHolder<BiddenItem> getItems(
            final Long userId,
            final ItemParam requestParam);

    /**
     * @param itemParam : item parameter
     * @return BiddenItem successfully placed for bidding
     * @see BiddenItemParam#BiddenItemParam(Long, String, String, Double)
     */
    BiddenItem bidItem(
            final Long userId,
            final BiddenItemParam itemParam);

    /**
     * @param userId : session User's ID
     */
    Boolean joinBidding(Long userId);
}
