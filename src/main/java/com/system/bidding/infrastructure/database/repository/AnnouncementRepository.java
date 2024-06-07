package com.system.bidding.infrastructure.database.repository;

import com.system.bidding.infrastructure.database.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
}
