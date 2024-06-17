package com.system.bidding.ports.outgoing.adapter.database;

import com.system.bidding.infrastructure.database.repository.BiddingHistoryRepository;
import com.system.bidding.infrastructure.database.repository.ItemRepository;
import com.system.bidding.infrastructure.mapstruct.ItemMapper;
import com.system.bidding.infrastructure.web.request.BiddenItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.ports.outgoing.BiddingManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class BiddingManagementDBAdapter implements BiddingManagementService {

    private final BiddingHistoryRepository historyRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    /**
     * @param requestParam : item param
     * @return List<BiddingHistory> instance
     * @see RequestPageableParam#RequestPageableParam(Optional, Optional, Optional, Optional)
     */
    public PagedListHolder<BiddenItem> getItems(
            final ItemParam requestParam) {
        return null;
    }

    /**
     * @param userId       : bidder's ID
     * @param requestParam : item param
     * @return List<BiddingHistory> instance
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     */
    @Override
    public PagedListHolder<BiddenItem> getItems(
            final Long userId,
            final ItemParam requestParam) {
        return null;
    }

    /**
     * @param userId    : bidder's id
     * @param itemParam : item parameter
     * @return BiddenItem successfully placed for bidding
     * @see ItemParam#ItemParam(Optional, Optional, Optional, Optional, Optional)
     */
    @Override
    public BiddenItem bidItem(
            final Long userId,
            final BiddenItemParam itemParam) {

        /*final var saveBiddenItem = Optional.of(itemRepository.findById(itemParam.id()))
                .ifPresent(itemEntity -> {
                    final var itemHistory = item
                });*/
        return null;
    }
}
