package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Data
@Entity
@Table(name = "table_bidding_histories")
@EqualsAndHashCode(callSuper = true)
public class BiddingHistoryEntity extends CommonEntity implements Serializable {

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

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ItemEntity itemEntity;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "bidder_id")
    private BidderEntity bidderEntity;

}
