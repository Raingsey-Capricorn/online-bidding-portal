package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.BiddingItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface BiddingDataController {

    /**
     * @return ResponseEntity as String
     */
    ResponseEntity<String> message();

    /**
     * @param requestParam :
     * @return ResponseEntity as List of item
     */
    ResponseEntity<List<Item>> getItems(
            final RequestPageableParam requestParam);

    /**
     * @param requestParam :bidding item request parameter
     * @return ResponseEntity as of an item
     */
    ResponseEntity<?> addItem(
            final RequestItemParam requestParam);

    /**
     * @param requestParam :bidding item request parameter
     * @return ResponseEntity as of an item
     */
    ResponseEntity<?> bidItem(
            final BiddingItemParam requestParam);

    /**
     * @param bidderId     : Bidder Id
     * @param requestParam :bidding parameter containing bidder info and additional arguments
     * @return List of items corresponding to the parameters
     */
    ResponseEntity<List<BiddingHistory>> biddingItems(
            final String bidderId,
            final ItemParam requestParam
    );

    /**
     * @param itemId : Bidder Id
     * @return List of items corresponding to the parameters
     */
    ResponseEntity<ItemBiddingDetails> itemDetails(
            final String itemId
    );

}
