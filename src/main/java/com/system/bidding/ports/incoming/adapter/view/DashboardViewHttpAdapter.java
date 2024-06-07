package com.system.bidding.ports.incoming.adapter.view;

import com.system.bidding.infrastructure.config.constants.URLEndpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Controller
@RequestMapping(URLEndpoints.BASE_VIEW_URL)
public class DashboardViewHttpAdapter {

    @GetMapping(URLEndpoints.DASHBOARD_URL)
    public String dashboard() {
        return "dashboard";
    }
}
