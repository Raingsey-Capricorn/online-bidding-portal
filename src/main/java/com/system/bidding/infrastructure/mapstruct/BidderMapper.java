package com.system.bidding.infrastructure.mapstruct;

import com.system.bidding.infrastructure.database.entity.BidderEntity;
import com.system.bidding.infrastructure.web.response.record.Bidder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BidderMapper {

    /**
     * @param bidder : argument
     * @return BidderEntity instance
     */
    @Mapping(source = "userName", target = "createdBy")
    BidderEntity from(Bidder bidder);

    /**
     * @param bidderEntity :argument
     * @return Bidder instance
     */
    Bidder from(BidderEntity bidderEntity);
}
