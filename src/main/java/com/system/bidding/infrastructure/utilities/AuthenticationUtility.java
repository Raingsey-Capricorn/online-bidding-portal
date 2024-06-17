package com.system.bidding.infrastructure.utilities;

import com.system.bidding.domain.business.UserEntityModel;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.ports.outgoing.UserModelService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AuthenticationUtility {

    /**
     * @param userModelService : user service for fetching data from DB
     * @return Map for model view
     */
    public static HashMap<String, Object> getUserRoleAndAccess(UserModelService userModelService) {

        final var map = new AtomicReference<HashMap<String, Object>>();
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.isNull(authentication)) {
            if (authentication.getPrincipal() instanceof UserEntityModel principal) {
                Optional.of((principal).getAuthorities().stream().findFirst()).ifPresent(
                        grantedAuthority -> {
                            final var authority = grantedAuthority.get().getAuthority();
                            map.set(new HashMap<>() {{
                                put("isAdmin", SecurityConstant.Authority.ADMIN.name().equals(authority));
                                put("role", SecurityConstant.Authority.valueOf(authority));
                            }});
                        });
            } else if (authentication.getPrincipal() instanceof OidcUser principal) {
                final var name = principal.getAttribute("name");
                final var existingUser = userModelService.loadUserByUsername(Objects.requireNonNull(name).toString());
                Optional.of((existingUser).getAuthorities().stream().findFirst()).ifPresent(
                        grantedAuthority -> {
                            final var authority = grantedAuthority.get().getAuthority();
                            map.set(new HashMap<>() {{
                                put("isAdmin", SecurityConstant.Authority.ADMIN.name().equals(authority));
                                put("role", SecurityConstant.Authority.valueOf(authority));
                            }});
                        });
            } else if (authentication.getPrincipal() instanceof OAuth2User principal) {
                final var name = principal.getAttribute("name");
                final var existingUser = userModelService.loadUserByUsername(Objects.requireNonNull(name).toString());
                Optional.of((existingUser).getAuthorities().stream().findFirst()).ifPresent(
                        grantedAuthority -> {
                            final var authority = grantedAuthority.get().getAuthority();
                            map.set(new HashMap<>() {{
                                put("isAdmin", SecurityConstant.Authority.ADMIN.name().equals(authority));
                                put("role", SecurityConstant.Authority.valueOf(authority));
                            }});
                        });
            }
        }
        return map.get();
    }
}
