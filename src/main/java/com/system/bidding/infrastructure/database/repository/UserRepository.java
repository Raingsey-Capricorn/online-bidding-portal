package com.system.bidding.infrastructure.database.repository;

import com.system.bidding.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * @param email : email's parameter
     * @return Optional<UserEntity> user data
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * @param userName : userName's parameter
     * @return Optional<UserEntity> user data
     */
    Optional<UserEntity> findByUserName(String userName);

}
