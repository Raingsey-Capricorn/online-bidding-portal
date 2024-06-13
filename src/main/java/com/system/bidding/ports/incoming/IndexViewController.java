package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.ItemParam;
import org.springframework.ui.Model;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface IndexViewController {

    /**
     * @param model : spring injected Model for data rendering
     * @return login view template
     */
    String loginView(final Model model);

    /**
     * @param model : spring injected Model for data rendering
     * @return logout view template
     */
    String logOutView(final Model model);

    /**
     * @param model : spring injected Model for data rendering
     * @return dashboard view template
     */
    String dashboardView(final Model model);

    /**
     * @param model : spring injected Model for data rendering
     * @return userItem view template
     */
    String Itemview(final Model model, final ItemParam requestParam);

}
