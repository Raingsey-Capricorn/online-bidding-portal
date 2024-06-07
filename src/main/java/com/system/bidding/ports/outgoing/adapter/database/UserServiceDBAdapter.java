package com.system.bidding.ports.outgoing.adapter.database;

import com.system.bidding.domain.business.UserEntityModel;
import com.system.bidding.infrastructure.database.repository.UserRepository;
import com.system.bidding.ports.outgoing.adapter.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceDBAdapter implements UserService {

    private final UserRepository repository;

    /**
     * @param details :
     */
    public UserEntityModel save(UserEntityModel details) {

        if (repository.findByEmail(details.getEmail()).isEmpty()) {
            log.info(">>>> Saving new user : {}", details.getUser().getEmail());
            return new UserEntityModel(repository.save(details.getUser()));
        } else log.info("<<<< User {} is existed, return the data from repository.", details.getEmail());
        return details;
    }

    /**
     * @return
     */
    @Override
    public UserDetailsService userDetailsService() {

        log.info("<<<< loaded user from database repository");
        return username -> repository.findByEmail(username)
                .map(UserEntityModel::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
