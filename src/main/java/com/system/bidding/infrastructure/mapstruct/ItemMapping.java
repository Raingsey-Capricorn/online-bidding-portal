package com.system.bidding.infrastructure.mapstruct;

import com.system.bidding.infrastructure.database.entity.ItemEntity;
import com.system.bidding.infrastructure.web.response.record.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Mapper(componentModel = "spring")
public interface ItemMapping {
    /**
     * @param entity : paged item data
     * @return Item record
     */
    List<Item> from(Page<ItemEntity> entity);

    /**
     * @param entity : list of item data
     * @return Item record
     */
    List<Item> from(List<ItemEntity> entity);
}
