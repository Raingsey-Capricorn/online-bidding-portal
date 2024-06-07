package com.system.bidding.ports.outgoing.adapter.database;

import com.system.bidding.infrastructure.database.repository.AnnouncementRepository;
import com.system.bidding.infrastructure.database.repository.BiddingHistoryRepository;
import com.system.bidding.infrastructure.database.repository.ItemRepository;
import com.system.bidding.infrastructure.mapstruct.ItemMapping;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import com.system.bidding.ports.outgoing.BiddingService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class BiddingServiceDBAdapter implements BiddingService {

    private final ItemRepository itemRepository;
    private final AnnouncementRepository announcementRepository;
    private final BiddingHistoryRepository biddingHistoryRepository;
    private final ItemMapping itemMapping;

    /**
     * @param pageable : page request
     * @return list of BiddingItem
     */
    @Override
    @SneakyThrows
    public PagedListHolder<Item> getItemList(final Pageable pageable) {

        final var listHolder = new PagedListHolder<Item>();
        listHolder.setSource(itemMapping.from(itemRepository.findAll()));
        listHolder.setPage(pageable.getPageNumber()-1);
        listHolder.setPageSize(pageable.getPageSize());
        return listHolder;
    }

    /**
     * @param clientId : clientId's parameter
     * @param pageable : pageable's parameter
     * @return list of Item
     */
    @Override
    public PagedListHolder<Item> getItemList(
            final Long clientId,
            final Pageable pageable) {

        log.warn("Not implementation yet");
        return null;
    }

    /**
     * @param bidderId     :Bidder's ID
     * @param requestParam : page request
     * @return list of BiddingHistory
     */
    @Override
    public PagedListHolder<BiddingHistory> getBiddingHistory(
            final Long bidderId,
            final ItemParam requestParam) {

        log.warn("Not implementation yet");
        return new PagedListHolder<>(Collections.emptyList());
    }

    /**
     * @param itemId : Item's ID
     * @return list of ItemBiddingDetails
     */
    @Override
    public ItemBiddingDetails getItemDetails(
            final Long itemId) {

        log.warn("Not implementation yet");
        return null;
    }

    /**
     * @param pageable : pageable's parameter
     * @return list of announcement
     */
    @Override
    public PagedListHolder<Announcement> getAnnouncements(
            final Pageable pageable) {
        log.warn("Not implementation yet");
        return null;
    }
}
