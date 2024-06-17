package com.system.bidding.ports.outgoing.adapter.json;

import com.system.bidding.infrastructure.utilities.JsonLoader;
import com.system.bidding.infrastructure.web.request.BiddenItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.ports.outgoing.BiddingManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
//@Primary
@Service
public class BiddingManagementFileAdapter implements BiddingManagementService {

    /**
     * @param requestParam : item param
     * @return List<BiddingHistory> instance
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    @Override
    public PagedListHolder<BiddenItem> getItems(
            final ItemParam requestParam) {

        final var listHolder = new PagedListHolder<BiddenItem>();
        final var data = new JsonLoader.History().load();
        listHolder.setSource(data.stream().toList());
        final var pageable = requestParam.createPageRequest();
        listHolder.setPage(pageable.getPageNumber() - 1);
        listHolder.setPageSize(pageable.getPageSize());
        return listHolder;
    }

    /**
     * @param userId       : bidder's ID
     * @param requestParam : item param
     * @return List<BiddingHistory> instance
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     */
    @Override
    public PagedListHolder<BiddenItem> getItems(Long userId, ItemParam requestParam) {

        final var listHolder = new PagedListHolder<BiddenItem>();
        final var data = new JsonLoader.History().load();
        requestParam.isWon()
                .ifPresentOrElse(aBoolean ->
                                listHolder.setSource(data.stream()
                                        .filter(history -> Objects.equals(history.bidder().id(), 1)
                                                && requestParam.isWon().get().equals(history.isWon())
                                        ).toList())
                        , () -> listHolder.setSource(data.stream()
                                .filter(history -> Objects.equals(history.bidder().id(), 1))
                                .toList())
                );
        final var pageable = requestParam.createPageRequest();
        listHolder.setPage(pageable.getPageNumber() - 1);
        listHolder.setPageSize(pageable.getPageSize());
        return listHolder;
    }

    /**
     * @param itemParam : item parameter
     * @return BiddenItem successfully placed for bidding
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     */
    @Override
    public BiddenItem bidItem(
            final Long userId,
            final BiddenItemParam itemParam) {
        return null;
    }
}
