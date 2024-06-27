package com.system.bidding.ports.outgoing.adapter.json;

import com.system.bidding.infrastructure.database.entity.ItemEntity;
import com.system.bidding.infrastructure.utilities.JsonLoader;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemDetails;
import com.system.bidding.ports.outgoing.ItemManagementService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
//@Primary
@Service
public class ItemManagementServiceFileAdapter implements ItemManagementService {

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

    @Override
    public PagedListHolder<Item> getBiddingItemBoardList(Pageable pageable) {
        return null;
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
     * @param itemId : Item's ID
     * @return ItemBiddingDetails
     */
    @Override
    @SneakyThrows
    public ItemDetails getItemDetails(Long itemId) {

        final var data = new JsonLoader.Details().load()
                .stream()
                .filter(details -> details.item().id().equals(itemId))
                .findFirst();
        return data.orElse(null);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public Item saveItem(ItemEntity item) {
        return null;
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

    /**
     * @param requestParam : pageable's parameter
     * @return list of bidden item
     */
    @Override
    public PagedListHolder<BiddenItem> getItemBiddingHistory(
            final Long userId,
            final Pageable requestParam) {

        final var listHolder = new PagedListHolder<BiddenItem>();
        listHolder.setSource(new JsonLoader.History().load());
        listHolder.setPage(requestParam.getPageNumber() - 1);
        listHolder.setPageSize(requestParam.getPageSize());
        return listHolder;
    }
}
