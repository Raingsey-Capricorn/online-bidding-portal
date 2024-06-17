package com.system.bidding.ports.incoming;

import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.Optional;

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
     * @param model        : spring injected Model for data rendering
     * @param requestParam : request param
     * @return Item view template
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    String itemsView(
            final Model model,
            final RequestPageableParam requestParam);

    /**
     * @param model        : model for data rendering
     * @param requestParam : request parameter
     * @return bidding view
     * @see Model#addAllAttributes(Map)
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    String biddingItemHistoryView(
            final Model model,
            final ItemParam requestParam);

    /**
     * @param model        : model for data rendering
     * @param requestParam : request parameter
     * @return bidding view
     * @see Model#addAllAttributes(Map)
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    String biddingBoardView(
            final Model model,
            final RequestPageableParam requestParam);

    /**
     * @param model        : model for data rendering
     * @param requestParam : request parameter
     * @return bidding view
     * @see Model#addAllAttributes(Map)
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    String announcementBoardView(
            final Model model,
            final RequestPageableParam requestParam);
}
