package com.system.bidding.infrastructure.database.repository;

import com.system.bidding.infrastructure.database.entity.BidderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Repository
public interface BidderRepository extends JpaRepository<BidderEntity, Long> {
}
