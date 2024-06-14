package com.system.bidding.ports.outgoing;

import com.system.bidding.domain.business.UserEntityModel;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface UserModelService extends UserDetailsService {

    /**
     * @param entityModel : entity model parameter
     * @return UserEntityModel instance
     */
    UserEntityModel save(UserEntityModel entityModel);

    /**
     * @param nameOrEmail : nameOrEmail (email as userName)
     * @return UserEntityModel instance
     */
    UserEntityModel findUserByUserName(String nameOrEmail);

}
