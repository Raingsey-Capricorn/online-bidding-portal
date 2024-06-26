package com.system.bidding.ports.incoming.adapter.view;

import com.system.bidding.domain.business.UserEntityModel;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.mapstruct.UserMapper;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.PageableResponseModel;
import com.system.bidding.ports.incoming.IndexViewController;
import com.system.bidding.ports.outgoing.ItemService;
import com.system.bidding.ports.outgoing.UserModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@RequiredArgsConstructor
@Controller("Index View Controller")
@RequestMapping(URLEndpoints.BASE_VIEW_URL)
@PreAuthorize(value = "hasAnyAuthority('ADMIN','SYSTEM','USER','ANONYMOUS')")
public class IndexViewHttpAdapter implements IndexViewController {

    private final ItemService itemService;
    private final UserModelService userModelService;
    private final UserMapper userMapper;

    /**
     * @param model : spring injected Model for data rendering
     * @return login view template
     */
    @Override
    @GetMapping(URLEndpoints.VIEW_LOGIN_URL)
    public String loginView(final Model model) {
        return "login";
    }

    /**
     * @param model : spring injected Model for data rendering
     * @return logout view template
     */
    @Override
    @GetMapping(URLEndpoints.VIEW_LOGOUT_URL)
    public String logOutView(final Model model) {
        return "login";
    }

    /**
     * @param model : spring injected Model for data rendering
     * @return dashboard view template
     */
    @Override
    @GetMapping(URLEndpoints.DASHBOARD_URL)
    public String dashboardView(final Model model) {

        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.isNull(authentication)) {
            if (authentication.getPrincipal() instanceof UserEntityModel principal) {
                Optional.of((principal).getAuthorities().stream().findFirst()).ifPresent(
                        grantedAuthority -> {
                            final var authority = grantedAuthority.get().getAuthority();
                            model.addAttribute("isAdmin", SecurityConstant.Authority.ADMIN.name().equals(authority));
                        });
            }
        }
        return "dashboard";
    }

    /**
     * @param model : spring injected Model for data rendering
     * @return userItem view template
     */
    @Override
    @GetMapping(URLEndpoints.BASE_ITEM_URL)
    public String ItemView(
            final Model model,
            final @ModelAttribute(name = "pageRequest") ItemParam requestParam) {

        final var securityContext = SecurityContextHolder.getContext();
        final var listHolder = itemService.getBiddingHistory(userMapper.mapUserId(securityContext, userModelService), requestParam);
        model.addAllAttributes(PageableResponseModel.pagingHistory(requestParam, listHolder));
        model.addAttribute("title", "Landing Page");
        return "admin/item_manage";
    }

}
