package com.system.bidding.ports.outgoing.adapter.database;

import com.system.bidding.infrastructure.database.entity.BidderEntity;
import com.system.bidding.infrastructure.database.entity.BiddingHistoryEntity;
import com.system.bidding.infrastructure.database.repository.BidderRepository;
import com.system.bidding.infrastructure.database.repository.BiddingHistoryRepository;
import com.system.bidding.infrastructure.database.repository.ItemRepository;
import com.system.bidding.infrastructure.database.repository.UserRepository;
import com.system.bidding.infrastructure.mapstruct.BidderMapper;
import com.system.bidding.infrastructure.mapstruct.BiddingItemHistoryMapper;
import com.system.bidding.infrastructure.mapstruct.ItemMapper;
import com.system.bidding.infrastructure.utilities.BidderType;
import com.system.bidding.infrastructure.web.request.BiddenItemParam;
import com.system.bidding.infrastructure.web.request.ItemParam;
import com.system.bidding.infrastructure.web.request.RequestPageableParam;
import com.system.bidding.infrastructure.web.response.record.BiddenItem;
import com.system.bidding.infrastructure.web.response.record.Bidder;
import com.system.bidding.ports.outgoing.BiddingManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
    private final BiddingItemHistoryMapper historyMapper;
    private final BidderRepository bidderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BidderMapper bidderMapper;
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

        final var biddenItem = new AtomicReference<BiddenItem>();
        itemRepository.findById(itemParam.id())
                .ifPresent(item -> {
                    bidderRepository.findBy(Example.of(
                                    new BidderEntity(
                                            Optional.empty(),
                                            Optional.of(userId),
                                            Optional.empty(),
                                            Optional.empty())
                            ), FluentQuery.FetchableFluentQuery::first)
                            .ifPresent(bidder -> {
                                final var date = new Date();
                                historyRepository.findBy(Example.of(
                                                new BiddingHistoryEntity(
                                                        Optional.empty(),
                                                        Optional.empty(),
                                                        Optional.of(item),
                                                        Optional.of(bidder))
                                        ), FluentQuery.FetchableFluentQuery::first)
                                        .ifPresentOrElse(entity -> {
                                            entity.setBiddingDate(date);
                                            entity.setBidderEntity(bidder);
                                            entity.setBiddingPrice(itemParam.price());
                                            entity.setRemark("Rebidding");
                                            historyRepository.save(entity);
                                        }, () -> {
                                            final var history = new BiddingHistoryEntity();
                                            history.setItemEntity(item);
                                            history.setBiddingDate(date);
                                            history.setBidderEntity(bidder);
                                            history.setBiddingPrice(itemParam.price());
                                            history.setCreatedBy(bidder.getUserName());
                                            history.setRemark("First Bidding");
                                            historyRepository.save(history);
                                        });
                                item.setMinBiddingPrice(item.getPrice());
                                item.setMaxBiddingPrice(itemParam.price());
                                itemRepository.save(item);
                                log.info(">>>> History saved: {}", item);
                            });
                });
        return biddenItem.get();
    }

    /**
     * @param userId : session User's ID
     */
    @Override
    public Boolean joinBidding(final Long userId) {

        final var status = new AtomicReference<>(false);
        userRepository.findById(userId)
                .ifPresent(userEntity -> {
                    final var date = new Date();
                    final var reference = new AtomicReference<BidderEntity>();
                    bidderRepository.findBy(Example.of(
                                    new BidderEntity(
                                            Optional.empty(),
                                            Optional.of(userId),
                                            Optional.empty(),
                                            Optional.empty())
                            ), FluentQuery.FetchableFluentQuery::first)
                            .ifPresentOrElse(entity -> {
                                        entity.setUpdatedBy(userEntity.getUserName());
                                        entity.setLoginTime(date);
                                        entity.setSessionStartDate(date);
                                        reference.set(entity);
                                    }, () -> reference.set(bidderMapper
                                            .from(Bidder.builder()
                                                    .authorizationServer(userEntity.getProvider().name())
                                                    .authorizationProvider(userEntity.getProvider())
                                                    .credential(userEntity.getRole().name())
                                                    .userName(userEntity.getUserName())
                                                    .type(BidderType.REGULAR)
                                                    .sessionStartDate(date)
                                                    .sessionDuration(2)
                                                    .loginTime(date)
                                                    .userId(userId)
                                                    .build()))
                            );
                    bidderRepository.save(reference.get());
                    log.info("Bidding user {} saved.", reference.get().getUserName());
                    status.set(true);
                });
        return status.get();
    }
}
