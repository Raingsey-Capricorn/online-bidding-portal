package com.system.bidding.ports.incoming.adapter.view;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.PageableResponseModel;
import com.system.bidding.ports.incoming.BiddingViewController;
import com.system.bidding.ports.outgoing.BiddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Controller(value = "Bidding Controller")
@RequestMapping(URLEndpoints.BASE_BIDDING_VIEW_URL)
@RequiredArgsConstructor
public class ItemBiddingViewHttpAdapter implements BiddingViewController {

    private final BiddingService biddingService;

    /**
     * @return dashboard view
     */
    @Override
    @GetMapping(value = URLEndpoints.DASHBOARD_URL)
    public String viewDashboard() {
        return "bidding/bidding_board";
    }

    @Override
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL)
    public String viewItems(
            final Model model,
            final @ModelAttribute(name = "pageRequest") RequestPageableParam requestParam) {

        final var listHolder = biddingService.getItemList(1L, requestParam.createPageRequest());
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

        final var listHolder = biddingService.getAnnouncements(requestParam.createPageRequest());
        model.addAllAttributes(PageableResponseModel.pagingAnnouncement(requestParam, listHolder));
        return "bidding/bidding_announcement";
    }

}