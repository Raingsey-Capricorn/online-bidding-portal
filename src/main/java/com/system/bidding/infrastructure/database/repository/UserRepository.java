package com.system.bidding.infrastructure.database.repository;

import com.system.bidding.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * @param email
     * @return
     */
    Optional<UserEntity> findByEmail(String email);
}
