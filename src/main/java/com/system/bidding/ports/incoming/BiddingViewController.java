package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import org.springframework.ui.Model;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
public interface BiddingViewController {

    /**
     *
     * @return Dashboard page
     */
    String viewDashboard();


    /**
     * @param model        : model for data attribute
     * @param requestParam : customized pageable request
     * @return : String name for template with model data
     */
    String viewItems(
            final Model model,
            final RequestPageableParam requestParam
    );

    /**
     * @param model        : model for data attribute
     * @param requestParam : customized pageable request
     * @return list of winner announcement
     */
    String viewAnnouncements(
            final Model model,
            final RequestPageableParam requestParam
    );

}
