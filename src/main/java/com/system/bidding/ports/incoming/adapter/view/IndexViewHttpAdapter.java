package com.system.bidding.ports.incoming.adapter.view;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.mapstruct.UserMapper;
import com.system.bidding.infrastructure.utilities.AuthenticationUtility;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.PageableResponse;
import com.system.bidding.ports.incoming.IndexViewController;
import com.system.bidding.ports.outgoing.ItemManagementService;
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

import java.util.Map;
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

    private final UserModelService userModelService;
    private final ItemManagementService itemManagementService;
    private final UserMapper userMapper;

    /**
     * @param model : spring injected Model for data rendering
     * @return login view template
     * @see Model#addAllAttributes(Map)
     */
    @Override
    @GetMapping(URLEndpoints.VIEW_LOGIN_URL)
    public String loginView(final Model model) {
        return "login";
    }

    /**
     * @param model : spring injected Model for data rendering
     * @return logout view template
     * @see Model#addAllAttributes(Map)
     */
    @Override
    @GetMapping(URLEndpoints.VIEW_LOGOUT_URL)
    public String logOutView(final Model model) {
        return "login";
    }

    /**
     * @param model : spring injected Model for data rendering
     * @return dashboard view template
     * @see Model#addAllAttributes(Map)
     */
    @Override
    @GetMapping(URLEndpoints.DASHBOARD_URL)
    public String dashboardView(final Model model) {

        model.addAllAttributes(AuthenticationUtility
                .getUserRoleAndAccess(userModelService));
        return "dashboard";
    }

    /**
     * @param model        : model for data rendering
     * @param requestParam : request parameter
     * @return bidding view
     * @see Model#addAllAttributes(Map)
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    @Override
    @GetMapping(value = URLEndpoints.BIDDING_ANNOUNCEMENT_URL)
    public String announcementBoardView(
            final Model model,
            final @ModelAttribute(name = "requestParam") RequestPageableParam requestParam) {

        final var listHolder = itemManagementService.getAnnouncements(requestParam.createPageRequest());
        model.addAllAttributes(PageableResponse.pagingAnnouncement(requestParam, listHolder));
        return "announcement";
    }

    /**
     * @param model        : model for data rendering
     * @param requestParam : request parameter
     * @return bidding view
     * @see Model#addAllAttributes(Map)
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    @Override
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping(URLEndpoints.BASE_ITEM_URL)
    public String itemsView(
            final Model model,
            final @ModelAttribute(name = "requestParam") RequestPageableParam requestParam) {

        final var context = SecurityContextHolder.getContext();
        final var userId = userMapper.mapUserId(context, userModelService);
        final var listHolder = itemManagementService.getItemList(userId, requestParam.createPageRequest());
        model.addAllAttributes(PageableResponse.pagingItems(requestParam, listHolder));
        model.addAttribute("title", "Item Management");
        return "management/item_manage";
    }

    /**
     * @param model        : model for data rendering
     * @param requestParam : request parameter
     * @return bidding view
     * @see Model#addAllAttributes(Map)
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     */
    @Override
    @GetMapping(URLEndpoints.BIDDING_HISTORY_VIEW_URL)
    @PreAuthorize(value = "hasAnyAuthority('USER','ANONYMOUS')")
    public String biddingItemHistoryView(
            final Model model,
            final @ModelAttribute(name = "requestParam") ItemParam requestParam) {

        final var context = SecurityContextHolder.getContext();
        final var userId = userMapper.mapUserId(context, userModelService);
        final var data = itemManagementService.getItemBiddingHistory(userId, requestParam.createPageRequest());
        model.addAllAttributes(PageableResponse.pagingHistory(requestParam, data));
        model.addAttribute("title", "Items History");
        return "bidding/bidding_history";
    }

    /**
     * @param model        : model for data rendering
     * @param requestParam : request parameter
     * @return bidding view
     * @see Model#addAllAttributes(Map)
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    @Override
    @GetMapping(URLEndpoints.BIDDING_BOARD_VIEW_URL)
    @PreAuthorize(value = "hasAnyAuthority('USER','ANONYMOUS')")
    public String biddingBoardView(
            final Model model,
            final @ModelAttribute(name = "requestParam") RequestPageableParam requestParam) {

        final var listHolder = itemManagementService.getBiddingItemBoardList(requestParam.createPageRequest());
        model.addAllAttributes(PageableResponse.pagingItems(requestParam, listHolder));
        return "bidding/bidding_board";
    }

}
