package com.system.bidding.ports.incoming.adapter.api;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.mapstruct.UserMapper;
import com.system.bidding.infrastructure.web.request.BiddenItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.infrastructure.web.response.record.BiddingSession;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemDetails;
import com.system.bidding.ports.incoming.BiddingRestController;
import com.system.bidding.ports.outgoing.BiddingManagementService;
import com.system.bidding.ports.outgoing.UserModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(URLEndpoints.BASE_BIDDING_API_URL)
@PreAuthorize(value = "hasAnyAuthority('USER','ANONYMOUS')")
public class BiddingRestHttpAdapter implements BiddingRestController {

    private final BiddingManagementService managementService;
    private final UserModelService userModelService;
    private final UserMapper userMapper;

    /**
     * @param requestParam : request parameter
     * @return ResponseEntity as List of item
     * @see ItemDetails#ItemDetails(Item, List, Date, Double, Double)
     */
    @Override
    @GetMapping(URLEndpoints.BASE_ITEM_URL)
    public ResponseEntity<List<BiddenItem>> getItems(final ItemParam requestParam) {

        final var context = SecurityContextHolder.getContext();
        final var userId = userMapper.mapUserId(context, userModelService);
        log.info(">>>> BiddingHistory for user : {}", userId);
        final var listHolder = managementService.getItems(userId, requestParam);
        return ResponseEntity.ok(listHolder.getPageList());
    }

    /**
     * @param requestParam :bidding item request parameter
     * @return ResponseEntity as of an item
     * @see ItemDetails#ItemDetails(Item, List, Date, Double, Double)
     */
    @Override
    @PostMapping(value = URLEndpoints.BIDDING_API_URL,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> applyBidding(
            final BiddenItemParam requestParam) {

        final var context = SecurityContextHolder.getContext();
        final var userId = userMapper.mapUserId(context, userModelService);
        log.info(">>>> Bidding item {} from user : {}", requestParam, userId);
        final var bidItem = managementService.bidItem(userId, requestParam);
        return ResponseEntity.ok(bidItem);
    }

    /**
     * @param itemId : Bidder Id
     * @return List of items corresponding to the parameters
     */
    @Override
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL + "/{id}")
    public ResponseEntity<ItemDetails> itemDetails(
            final @PathVariable(value = "id") String itemId) {
        return null;
    }

    /**
     * @return biddingInfo
     * @see BiddingSession#BiddingSession(Long, String, String, Long, String)
     */
    @Override
    @PostMapping(value = URLEndpoints.BIDDING_REQUEST_API_URL)
    public ResponseEntity<BiddingSession> biddingRequest() {

        //TODO : apply caching programmatically here
        final var context = SecurityContextHolder.getContext();
        final var userId = userMapper.mapUserId(context, userModelService);
        log.info(">>>> New request to join bidding from user: {}", userId);
        return null;
    }


/*

    @Override
    @GetMapping("/bidding")
    public ResponseEntity<String> message() {
        return ResponseEntity.ok("Hello !");
    }

    @Override
//    @GetMapping(value = URLEndpoints.BASE_ITEM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItems(
            final @ModelAttribute(name = "pageRequest") RequestPageableParam requestParam) {

        log.info("requestParam: {}", requestParam);
        return ResponseEntity.ok(itemService.getItemList(requestParam.createPageRequest()).getPageList());
    }

    @Override
//    @PostMapping(value = URLEndpoints.BASE_ITEM_URL + "/add",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> applyBidding(RequestItemParam requestParam) {

        log.error("Received request to save item {}", requestParam);
        return ResponseEntity.ok().build();
    }

    @Override
//    @PostMapping(value = URLEndpoints.BIDDING_URL + "/bid",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> bidItem(BiddingItemParam requestParam) {

        log.error("Received request to save item {}", requestParam);
        return ResponseEntity.ok().build();
    }
    @Override
//    @GetMapping(value = "/{id}" + URLEndpoints.BASE_ITEM_URL,
//            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BiddingHistory>> biddingItems(
            final @PathVariable(name = "id") String bidderId,
            final @ModelAttribute(name = "pageRequest") ItemParam requestParam) {

        log.info("Bidder info : ID : {}, requestParam: {}", bidderId, requestParam);
        return ResponseEntity.ok(itemService.getBiddingHistory(
                        null,
                        requestParam)
                .getPageList()
        );
    }

    @Override
//    @GetMapping(value = URLEndpoints.BASE_ITEM_URL + "/{id}/details",
//            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDetails> itemDetails(
            final @PathVariable(name = "id") String itemId) {

        log.info("itemId: {}", itemId);
        return ResponseEntity.ok(itemService.getItemDetails(Long.parseLong(itemId)));
    }
*/

}
