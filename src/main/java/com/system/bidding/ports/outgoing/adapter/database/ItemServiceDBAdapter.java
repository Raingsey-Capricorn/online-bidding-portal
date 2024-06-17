package com.system.bidding.ports.outgoing.adapter.database;

import com.system.bidding.infrastructure.database.entity.ItemEntity;
import com.system.bidding.infrastructure.database.repository.ItemRepository;
import com.system.bidding.infrastructure.mapstruct.ItemMapper;
import com.system.bidding.infrastructure.mapstruct.UserMapper;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemDetails;
import com.system.bidding.ports.outgoing.ItemService;
import com.system.bidding.ports.outgoing.UserModelService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ItemServiceDBAdapter implements ItemService {

    private final UserModelService userModelService;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserMapper userMapper;

    /**
     * @param pageable : page request
     * @return list of BiddingItem
     */
    @Override
    @SneakyThrows
    public PagedListHolder<Item> getItemList(final Pageable pageable) {

        final var holder = new PagedListHolder<Item>();
        holder.setSource(itemMapper.from(itemRepository.findAll()));
        holder.setPage(pageable.getPageNumber() - 1);
        holder.setPageSize(pageable.getPageSize());
        return holder;
    }

    @Override
    public PagedListHolder<Item> getBiddingItemBoardList(Pageable pageable) {

        final var holder = new PagedListHolder<Item>();
        final var itemEntity = new ItemEntity();
        itemEntity.setAvailability(true);
        holder.setSource(itemMapper.from(itemRepository.findAll(Example.of(itemEntity))));
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

        final var holder = new PagedListHolder<Item>();
        final var itemEntity = new ItemEntity();
        itemEntity.setClientId(userId);
        holder.setSource(itemMapper.from(itemRepository.findAll(Example.of(itemEntity))));
        holder.setPage(pageable.getPageNumber() - 1);
        holder.setPageSize(pageable.getPageSize());
        return holder;
    }

    /**
     * @param itemId : Item's ID
     * @return list of ItemBiddingDetails
     */
    @Override
    public ItemDetails getItemDetails(
            final Long itemId) {


        return null;
    }

    /**
     * @param item : item to save
     * @return saved item
     * @see ItemEntity#ItemEntity()
     */
    @Override
    public Item saveItem(final ItemEntity item) {

        final var securityContext = SecurityContextHolder.getContext();
        final var userId = userMapper.mapUserId(securityContext, userModelService);
        item.setClientId(userId);
        log.info(">>>> Item is being saved");
        return itemMapper.from(itemRepository.save(item));
    }

    /**
     * @param pageable : pageable's parameter
     * @return list of announcement
     */
    @Override
    public PagedListHolder<Announcement> getAnnouncements(
            final Pageable pageable) {
        log.warn("Not implementation yet");
        return new PagedListHolder<>();
    }

    @Override
    public PagedListHolder<BiddenItem> getItemBiddingHistory(Pageable pageable) {
        return new PagedListHolder<>();
    }


}
