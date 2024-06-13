package com.system.bidding.ports.incoming.adapter.view;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.PageableResponseModel;
import com.system.bidding.ports.outgoing.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Controller(value = "Landing View Controller")
@RequiredArgsConstructor
@RequestMapping(URLEndpoints.BASE_USER_VIEW_URL)
@PreAuthorize(value = "hasAnyAuthority('ADMIN','SYSTEM','USER','ANONYMOUS')")
public class ItemUserViewHttpAdapter {

    private final ItemService itemService;

    /**
     * @param model        : model for data attribute
     * @param requestParam : customized pageable request
     * @return : String name for template with model data
     */
    @GetMapping(value = "/{id}" + URLEndpoints.BASE_ITEM_URL)
    public String viewItems(
            final Model model,
            final @PathVariable(name = "id") String bidderId,
            final @ModelAttribute(name = "pageRequest") ItemParam requestParam) {

        final var listHolder = itemService.getBiddingHistory(null, requestParam);
        model.addAllAttributes(PageableResponseModel.pagingHistory(requestParam, listHolder));
        model.addAttribute("title", "Landing Page");
        return "user/item_history";
    }


}