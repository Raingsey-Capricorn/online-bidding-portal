package com.system.bidding.infrastructure.mapstruct;

import com.system.bidding.domain.business.UserEntityModel;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.config.security.response.OAuth2UserResponse;
import com.system.bidding.infrastructure.config.security.response.OidCUserResponse;
import com.system.bidding.infrastructure.web.request.SignUpParam;
import com.system.bidding.ports.outgoing.UserModelService;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * @param securityContext  : security context
     * @param userModelService : user service
     * @return User'ID as Long
     */
    default Long mapUserId(
            final SecurityContext securityContext,
            final UserModelService userModelService) {

        final var principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof OidcUser) {
            final var name = ((OidCUserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("name");
            return ((UserEntityModel) userModelService.loadUserByUsername(name.toString())).getUser().getId();
        } else if (principal instanceof OAuth2User) {
            final var name = ((OAuth2UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttribute("name");
            return ((UserEntityModel) userModelService.loadUserByUsername(Objects.requireNonNull(name).toString())).getUser().getId();
        } else if (principal instanceof UserEntityModel) {
            return ((UserEntityModel) principal).getUser().getId();
        }
        return 0L;
    }

    /**
     * @param oAuth2User : oAuth2User's parameter
     * @return UserEntityModel for saving to DB
     */
    default UserEntityModel from(final OAuth2User oAuth2User) {

        final var model = new UserEntityModel();
        final var attributes = oAuth2User.getAttributes();
        model.setFirstName(Optional.ofNullable(attributes.get("name")).orElse("").toString())
                .setLastName(Optional.ofNullable(attributes.get("name")).orElse("").toString())
                .setEmail(Optional.ofNullable(attributes.get("email")).orElse("").toString())
                .setRole(SecurityConstant.Authority.ANONYMOUS);
        model.getUser().setProvider(SecurityConstant.AuthorizationProvider.GIT_HUB);
        model.getUser().setCreatedBy(Optional.ofNullable(attributes.get("name")).orElse("").toString());
        model.getUser().setUserName(Optional.ofNullable(attributes.get("name")).orElse("").toString());
        return model;
    }

    /**
     * @param oidcUser : oidcUser's parameter
     * @return UserEntityModel for saving to DB
     */
    default UserEntityModel from(final OidcUser oidcUser) {

        final var model = new UserEntityModel();
        final var attributes = oidcUser.getAttributes();
        model.setFirstName(Optional.ofNullable(attributes.get("family_name")).orElse("").toString())
                .setLastName(Optional.ofNullable(attributes.get("given_name")).orElse("").toString())
                .setEmail(Optional.ofNullable(attributes.get("email")).orElse("").toString())
                .setRole(SecurityConstant.Authority.ANONYMOUS);
        model.getUser().setProvider(SecurityConstant.AuthorizationProvider.GOOGLE);
        model.getUser().setCreatedBy(Optional.ofNullable(attributes.get("name")).orElse("").toString());
        model.getUser().setUserName(Optional.ofNullable(attributes.get("name")).orElse("").toString());
        return model;
    }

    /**
     * @param signUpParam     : signUpParam's parameter
     * @param passwordEncoder : passwordEncoder parameter
     * @return UserEntityModel instance
     */
    default UserEntityModel from(
            final @Nonnull SignUpParam signUpParam,
            final @Nonnull PasswordEncoder passwordEncoder) {

        final var model = new UserEntityModel()
                .setFirstName(signUpParam.firstName())
                .setLastName(signUpParam.lastName())
                .setEmail(signUpParam.email())
                .setPassword(passwordEncoder.encode(signUpParam.password().get()))
                .setRole(SecurityConstant.Authority.valueOf(signUpParam.role().get().name()));
        model.getUser().setProvider(SecurityConstant.AuthorizationProvider.SSUP);
        model.getUser().setCreatedBy(signUpParam.userName());
        model.getUser().setUserName(signUpParam.email());
        return model;
    }

}
