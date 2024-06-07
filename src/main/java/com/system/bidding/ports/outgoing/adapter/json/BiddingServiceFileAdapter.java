package com.system.bidding.ports.outgoing.adapter.json;

import com.system.bidding.infrastructure.utilities.JsonLoader;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import com.system.bidding.ports.outgoing.BiddingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
//@Primary
@Service
public class BiddingServiceFileAdapter implements BiddingService {

    /**
     * @param pageable : page request
     * @return List of item
     */
    @Override
    @SneakyThrows
    public PagedListHolder<Item> getItemList(final Pageable pageable) {

        final var listHolder = new PagedListHolder<Item>();
        listHolder.setSource(new JsonLoader.Items().load());
        listHolder.setPage(pageable.getPageNumber() - 1);
        listHolder.setPageSize(pageable.getPageSize());
        return listHolder;
    }

    /**
     * @param clientId : clientId's parameter
     * @param pageable : pageable's parameter
     * @return List of item
     */
    @Override
    @SneakyThrows
    public PagedListHolder<Item> getItemList(
            final Long clientId,
            final Pageable pageable) {

        final var listHolder = new PagedListHolder<Item>();
        listHolder.setSource(
                new JsonLoader.Items()
                        .load()
                        .stream()
                        .filter(Item::availability)
                        .toList()
        );
        listHolder.setPage(pageable.getPageNumber() - 1);
        listHolder.setPageSize(pageable.getPageSize());
        return listHolder;
    }

    /**
     * @param bidderId     :Bidder's ID
     * @param requestParam : page request
     * @return Bidding history
     */
    @Override
    @SneakyThrows
    public PagedListHolder<BiddingHistory> getBiddingHistory(
            final Long bidderId,
            final ItemParam requestParam) {

        final var listHolder = new PagedListHolder<BiddingHistory>();
        final var data = new JsonLoader.History().load();
        requestParam.isWon()
                .ifPresentOrElse(aBoolean ->
                                listHolder.setSource(data.stream()
                                        .filter(history -> Objects.equals(history.bidder().id(), bidderId)
                                                && requestParam.isWon().get().equals(history.isWon())
                                        ).toList())
                        , () -> listHolder.setSource(data.stream()
                                .filter(history -> Objects.equals(history.bidder().id(), bidderId))
                                .toList())
                );
        final var pageable = requestParam.createPageRequest();
        listHolder.setPage(pageable.getPageNumber() - 1);
        listHolder.setPageSize(pageable.getPageSize());
        return listHolder;
    }

    /**
     * @param itemId : Item's ID
     * @return ItemBiddingDetails
     */
    @Override
    @SneakyThrows
    public ItemBiddingDetails getItemDetails(Long itemId) {

        final var data = new JsonLoader.Details().load()
                .stream()
                .filter(details -> details.item().id().equals(itemId))
                .findFirst();
        return data.orElse(null);
    }

    /**
     * @param pageable : pageable's parameter
     * @return list of announcement
     */
    @Override
    public PagedListHolder<Announcement> getAnnouncements(
            final Pageable pageable) {

        final var listHolder = new PagedListHolder<Announcement>();
        listHolder.setSource(new JsonLoader.BiddingAnnouncement().load());
        listHolder.setPage(pageable.getPageNumber() - 1);
        listHolder.setPageSize(pageable.getPageSize());
        return listHolder;
    }
}
