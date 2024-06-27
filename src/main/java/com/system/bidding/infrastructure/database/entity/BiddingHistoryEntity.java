package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "table_bidding_histories")
@EqualsAndHashCode(callSuper = true)
public class BiddingHistoryEntity
        extends CommonEntity
        implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_won")
    private Boolean isWon;

    @Column(name = "bidding_date",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date biddingDate;

    @Column(name = "bidding_price")
    private Double biddingPrice;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private BidderEntity bidderEntity;

    /**
     * @param id           : History ID
     * @param isWon        : indicate is the record is won
     * @param itemEntity   : item
     * @param bidderEntity : bidder
     */
    public BiddingHistoryEntity(
            final Optional<Long> id,
            final Optional<Boolean> isWon,
            final Optional<ItemEntity> itemEntity,
            final Optional<BidderEntity> bidderEntity) {

        this.id = id.orElse(null);
        this.isWon = isWon.orElse(null);
        this.itemEntity = itemEntity.orElse(null);
        this.bidderEntity = bidderEntity.orElse(null);
    }

}
