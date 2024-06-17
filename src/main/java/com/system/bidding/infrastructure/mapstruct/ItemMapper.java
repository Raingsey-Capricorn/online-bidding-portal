package com.system.bidding.infrastructure.mapstruct;

import com.system.bidding.infrastructure.database.entity.ItemEntity;
import com.system.bidding.infrastructure.web.request.RequestItemParam;
import com.system.bidding.infrastructure.web.response.record.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {

    /**
     * @param entity : paged item data
     * @return Item record
     * @see ItemEntity
     */
    List<Item> from(final Page<ItemEntity> entity);

    /**
     * @param entity : list of item data
     * @return Item record
     * @see ItemEntity
     */
    List<Item> from(final List<ItemEntity> entity);

    /**
     * @param item : item request
     * @return ItemEntity
     * @see ItemEntity
     */
    ItemEntity from(final RequestItemParam item);

    /**
     * @param savedItem : item entity
     * @return ItemEntity
     * @see ItemEntity
     */
    Item from(final ItemEntity savedItem);

}
