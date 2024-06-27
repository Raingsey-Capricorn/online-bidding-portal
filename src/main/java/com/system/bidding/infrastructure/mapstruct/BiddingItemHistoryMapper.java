package com.system.bidding.infrastructure.mapstruct;

import com.system.bidding.infrastructure.database.entity.BiddingHistoryEntity;
import com.system.bidding.infrastructure.web.response.record.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BiddingItemHistoryMapper {

    /**
     * @param item
     * @return
     */
    BiddingHistoryEntity from(Item item);

}
