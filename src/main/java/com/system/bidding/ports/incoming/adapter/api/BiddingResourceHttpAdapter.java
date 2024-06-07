package com.system.bidding.ports.incoming.adapter.api;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.web.request.BiddingItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import com.system.bidding.ports.incoming.BiddingDataController;
import com.system.bidding.ports.outgoing.BiddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@RestController
@RequestMapping(URLEndpoints.BASE_API_URL)
@RequiredArgsConstructor
public class BiddingResourceHttpAdapter implements BiddingDataController {

    private final BiddingService biddingService;

    @Override
    @GetMapping("/bidding")
    public ResponseEntity<String> message() {
        return ResponseEntity.ok("Hello !");
    }

    /**
     * @param requestParam :
     * @return ResponseEntity as List of item
     */
    @Override
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItems(
            final @ModelAttribute(name = "pageRequest") RequestPageableParam requestParam) {

        log.info("requestParam: {}", requestParam);
        return ResponseEntity.ok(biddingService.getItemList(requestParam.createPageRequest()).getPageList());
    }

    /**
     * @param requestParam :bidding item request parameter
     * @return ResponseEntity as of an item
     */
    @Override
    @PostMapping(value = URLEndpoints.BASE_ITEM_URL + "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addItem(RequestItemParam requestParam) {

        log.error("Received request to save item {}", requestParam);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(value = URLEndpoints.BIDDING_URL + "/bid",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> bidItem(BiddingItemParam requestParam) {
        
        log.error("Received request to save item {}", requestParam);
        return ResponseEntity.ok().build();
    }

    /**
     * @param bidderId     : Bidder Id
     * @param requestParam :bidding parameter containing bidder info and additional arguments
     * @return List of items corresponding to the parameters
     */
    @Override
    @GetMapping(value = "/{id}" + URLEndpoints.BASE_ITEM_URL,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BiddingHistory>> biddingItems(
            final @PathVariable(name = "id") String bidderId,
            final @ModelAttribute(name = "pageRequest") ItemParam requestParam) {

        log.info("Bidder info : ID : {}, requestParam: {}", bidderId, requestParam);
        return ResponseEntity.ok(biddingService.getBiddingHistory(
                        Long.parseLong(bidderId),
                        requestParam)
                .getPageList()
        );
    }

    @Override
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL + "/{id}/details",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemBiddingDetails> itemDetails(
            final @PathVariable(name = "id") String itemId) {

        log.info("itemId: {}", itemId);
        return ResponseEntity.ok(biddingService.getItemDetails(Long.parseLong(itemId)));
    }

}
