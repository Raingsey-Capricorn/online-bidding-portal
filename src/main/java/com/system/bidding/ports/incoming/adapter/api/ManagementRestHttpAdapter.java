package com.system.bidding.ports.incoming.adapter.api;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.mapstruct.ItemMapper;
import com.system.bidding.infrastructure.web.request.RequestItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemDetails;
import com.system.bidding.ports.incoming.ManagementRestController;
import com.system.bidding.ports.outgoing.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(URLEndpoints.BASE_API_URL)
@PreAuthorize(value = "hasAnyAuthority('ADMIN','SYSTEM','USER','ANONYMOUS')")
public class ManagementRestHttpAdapter implements ManagementRestController<Item> {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    /**
     * @param item :item to create
     * @return ResponseEntity with saved Item
     * @see RequestItemParam#RequestItemParam(String, String, Boolean, Double)
     * @see Item#Item(Long, String, Long, String, Boolean, Double, Double, Double, Date, Date)
     */
    @Override
    @PostMapping(value = URLEndpoints.BASE_ITEM_URL,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Item> createItem(RequestItemParam item) {

        final var body = Optional.of(itemMapper.from(item))
                .map(itemService::saveItem)
                .orElse(null);
        log.info("Create Item: {}", body);
        return ResponseEntity.ok(body);
    }

    /**
     * @param item : item to update
     * @return ResponseEntity with saved Item
     * @see RequestItemParam#RequestItemParam(String, String, Boolean, Double)
     * @see Item#Item(Long, String, Long, String, Boolean, Double, Double, Double, Date, Date)
     */
    @Override
    @PutMapping(value = URLEndpoints.BASE_ITEM_URL,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Item> updateItem(
            final Long itemId,
            final RequestItemParam item) {

        log.error("Received request to save item {}", item);
        return ResponseEntity.ok().build();
    }

    /**
     * @param itemId : item Id to delete
     * @return deleted item
     * @see Item#Item(Long, String, Long, String, Boolean, Double, Double, Double, Date, Date)
     */
    @Override
    @DeleteMapping(value = URLEndpoints.BASE_ITEM_URL)
    public ResponseEntity<Item> deleteItem(final Long itemId) {

        log.error("Received request to save item {}", itemId);
        return ResponseEntity.ok().build();
    }

    /**
     * @param itemId : item Id
     * @return ResponseEntity containing item details
     */
    @Override
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL + "/{id}")
    public ResponseEntity<ItemDetails> readItem(@PathVariable(value = "id") final Long itemId) {

        log.info(">>>> Received request to get item {}", itemId);
        final var itemById = Optional.of(itemService.getItemDetails(itemId))
                .orElse(null);
        return ResponseEntity.ok(itemById);
    }

    /**
     * @param requestParam : request parameter
     * @return List of item
     * @see RequestItemParam#RequestItemParam(String, String, Boolean, Double)
     */
    @Override
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL)
    public ResponseEntity<List<Item>> listItems(final RequestPageableParam requestParam) {

        log.error("Received request to save item {}", requestParam);
        return ResponseEntity.ok().build();
    }
}
