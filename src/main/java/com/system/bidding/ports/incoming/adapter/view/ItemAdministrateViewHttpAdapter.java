package com.system.bidding.ports.incoming.adapter.view;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.PageableResponseModel;
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
@Controller(value = "Back Office View Controller")
@RequestMapping(URLEndpoints.BASE_ADMIN_VIEW_URL)
@RequiredArgsConstructor
public class ItemAdministrateViewHttpAdapter {

    private final BiddingService biddingService;

    /**
     * @param model        : model for data attribute
     * @param requestParam : customized pageable request
     * @return : String name for template with model data
     */
    @GetMapping(value = URLEndpoints.BASE_ITEM_URL)
    public String viewItems(
            final Model model,
            final @ModelAttribute(name = "pageRequest") RequestPageableParam requestParam) {

        final var listHolder = biddingService.getItemList(requestParam.createPageRequest());
        model.addAllAttributes(PageableResponseModel.pagingItems(requestParam, listHolder));
        return "admin/item_manage";
    }

}