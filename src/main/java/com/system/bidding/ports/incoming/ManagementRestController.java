package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.RequestItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.ItemDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface ManagementRestController<T> {

    /**
     * @param item :item to create
     * @return generic T
     */
    ResponseEntity<T> createItem(final RequestItemParam item);

    /**
     * @param item : item to update
     * @return generic T
     */
    ResponseEntity<T> updateItem(final Long itemId, final RequestItemParam item);

    /**
     * @param itemId : item to delete
     * @return generic T
     */
    ResponseEntity<T> deleteItem(final Long itemId);

    /**
     * @param requestParam :
     * @return generic T
     */
    ResponseEntity<List<T>> listItems(final RequestPageableParam requestParam);

    /**
     * @param itemId :
     * @return generic T
     */
    ResponseEntity<ItemDetails> readItem(final Long itemId);

}
