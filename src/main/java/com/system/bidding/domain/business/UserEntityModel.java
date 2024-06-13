package com.system.bidding.domain.business;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.database.entity.UserEntity;
import com.system.bidding.infrastructure.database.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class UserEntityModel implements User {

    @Setter(AccessLevel.PUBLIC)
    private UserEntity user = new UserEntity();

    public UserEntityModel(UserEntity user) {
        this.user = user;
    }


    /**
     * @param role
     * @return
     */
    public UserEntityModel setRole(SecurityConstant.AuthorizationRole role) {
        this.user.setRole(role);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    /**
     * @return
     */
    public String getFirstName() {
        return user.getFirstName();
    }

    /**
     * @param firstName
     */
    public UserEntityModel setFirstName(String firstName) {
        this.user.setFirstName(firstName);
        return this;
    }

    /**
     * @return
     */
    public String getLastName() {
        return user.getLastName();
    }

    /**
     * @param lastName
     */
    public UserEntityModel setLastName(String lastName) {
        this.user.setLastName(lastName);
        return this;
    }

    /**
     * @return
     */
    public String getEmail() {
        return user.getEmail();
    }

    /**
     * @param email
     */
    public UserEntityModel setEmail(String email) {
        this.user.setEmail(email);
        return this;
    }

    /**
     * @param password
     */
    public UserEntityModel setPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
