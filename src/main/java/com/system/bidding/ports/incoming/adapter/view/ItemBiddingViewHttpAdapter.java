package com.system.bidding.ports.incoming.adapter.view;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.PageableResponseModel;
import com.system.bidding.ports.incoming.BiddingViewController;
import com.system.bidding.ports.outgoing.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@RequiredArgsConstructor
@Controller(value = "Bidding Controller")
@RequestMapping(URLEndpoints.BASE_BIDDING_VIEW_URL)
@PreAuthorize(value = "hasAnyAuthority('ADMIN','SYSTEM','USER','ANONYMOUS')")
public class ItemBiddingViewHttpAdapter implements BiddingViewController {

    private final ItemService itemService;

    /**
     * @param model        : model for data attribute
     * @param requestParam : customized pageable request
     * @return
     */
    @Override
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL)
    public String viewItems(
            final Model model,
            final @ModelAttribute(name = "pageRequest") RequestPageableParam requestParam) {

        final var listHolder = itemService.getItemList(null, requestParam.createPageRequest());
        model.addAllAttributes(PageableResponseModel.pagingItems(requestParam, listHolder));
        return "bidding/bidding_item";
    }

    /**
     * @param model        : model for data attribute
     * @param requestParam : customized pageable request
     * @return
     */
    @Override
    @GetMapping(value = URLEndpoints.BIDDING_ANNOUNCEMENT_URL)
    public String viewAnnouncements(
            final Model model,
            final @ModelAttribute(name = "pageRequest") RequestPageableParam requestParam) {

        final var listHolder = itemService.getAnnouncements(requestParam.createPageRequest());
        model.addAllAttributes(PageableResponseModel.pagingAnnouncement(requestParam, listHolder));
        return "bidding/bidding_announcement";
    }

}