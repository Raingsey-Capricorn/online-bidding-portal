package com.system.bidding.ports.outgoing.adapter.json;

import com.system.bidding.infrastructure.utilities.JsonLoader;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import com.system.bidding.ports.outgoing.ItemService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Primary
@Service
public class ItemServiceFileAdapter implements ItemService {

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
     * @param userId   : security principal
     * @param pageable : pageable's parameter
     * @return List of item
     */
    @Override
    @SneakyThrows
    public PagedListHolder<Item> getItemList(
            final Long userId,
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
     * @param userId       : security principal
     * @param requestParam : page request
     * @return list of BiddingHistory
     */
    @Override
    @SneakyThrows
    public PagedListHolder<BiddingHistory> getBiddingHistory(
            final Long userId,
            final ItemParam requestParam) {

        final var listHolder = new PagedListHolder<BiddingHistory>();
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
