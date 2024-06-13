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
import com.system.bidding.ports.outgoing.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Service
//@Primary
@RequiredArgsConstructor
public class ItemServiceDBAdapter implements ItemService {

    private final BiddingHistoryRepository biddingHistoryRepository;
    private final AnnouncementRepository announcementRepository;
    private final ItemRepository itemRepository;
    private final ItemMapping itemMapping;

    /**
     * @param pageable : page request
     * @return list of BiddingItem
     */
    @Override
    @SneakyThrows
    public PagedListHolder<Item> getItemList(final Pageable pageable) {

        final var holder = new PagedListHolder<Item>();
        holder.setSource(itemMapping.from(itemRepository.findAll()));
        holder.setPage(pageable.getPageNumber() - 1);
        holder.setPageSize(pageable.getPageSize());
        return holder;
    }

    /**
     * @param userId   : security principal
     * @param pageable : pageable's parameter
     * @return list of Item
     */
    @Override
    public PagedListHolder<Item> getItemList(
            final Long userId,
            final Pageable pageable) {

        log.warn("Not implementation yet");
        return null;
    }

    /**
     * @param userId       : security principal
     * @param requestParam : page request
     * @return list of BiddingHistory
     */
    @Override
    public PagedListHolder<BiddingHistory> getBiddingHistory(
            final Long userId,
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
