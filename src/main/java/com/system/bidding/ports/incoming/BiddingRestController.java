package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.BiddenItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.infrastructure.web.response.record.BiddingSession;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemDetails;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface BiddingRestController {

    /**
     * @param requestParam :
     * @return ResponseEntity as List of item
     * @see ItemDetails#ItemDetails(Item, List, Date, Double, Double)
     */
    ResponseEntity<List<BiddenItem>> getItems(
            final ItemParam requestParam);

    /**
     * @param requestParam :bidding item request parameter
     * @return ResponseEntity as of an item
     * @see BiddenItemParam#BiddenItemParam(Long, String, String, Double, Optional)
     */
    ResponseEntity<?> applyBidding(
            final BiddenItemParam requestParam);

    /**
     * @param itemId : Bidder Id
     * @return List of items corresponding to the parameters
     */
    ResponseEntity<ItemDetails> itemDetails(
            final String itemId
    );

    /**
     * @return bidding session for current user
     */
    ResponseEntity<BiddingSession> biddingRequest();

}
