package com.system.bidding.ports.outgoing.adapter.database;

import com.system.bidding.domain.business.UserEntityModel;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.database.entity.UserEntity;
import com.system.bidding.infrastructure.database.repository.UserRepository;
import com.system.bidding.ports.outgoing.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
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
     * @param entityModel : entity model parameter
     * @return UserEntityModel instance
     */
    @Override
    public UserEntityModel save(UserEntityModel entityModel) {

        final var user = new UserEntity();
        user.setUserName(entityModel.getUser().getUserName());
        user.setEmail(entityModel.getEmail());
        final var existingUser = repository.findBy(Example.of(user), FluentQuery.FetchableFluentQuery::first);
        if (existingUser.isEmpty()) {
            log.info(">>>> Saving new user : {}", entityModel.getUser().getEmail());
            return new UserEntityModel(repository.save(entityModel.getUser()));
        } else {
            log.info("<<<< User {} is existed, return the data from repository.", entityModel.getEmail());
            entityModel.setUser(existingUser.get());
            return entityModel;
        }
    }

    /**
     * @param nameOrEmail : nameOrEmail (email as userName)
     * @return UserDetails instance
     */
    @Override
    public UserEntityModel findUserByUserName(String nameOrEmail) {
        final var user = new UserEntity();
        user.setUserName(nameOrEmail);
        user.setEmail(nameOrEmail);
        user.setRole(SecurityConstant.AuthorizationRole.ANONYMOUS);
        return repository.findBy(Example.of(user), FluentQuery.FetchableFluentQuery::first)
                .map(UserEntityModel::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * @param username the username identifying the user whose data is required.
     * @return UserDetails instance
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("<<<< loaded user from database repository");
        return repository.findByUserName(username)
                .map(UserEntityModel::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}