package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */

@Data
@Entity
@Table(name = "table_announcements")
@EqualsAndHashCode(callSuper = true)
public class AnnouncementEntity extends CommonEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "bidder_id")
    private BidderEntity bidderEntity;

    @Column(name = "bidding_date")
    private Date biddingDate;

    @Column(name = "description")
    private String description;

    @Column(name = "bidder_attend")
    private Integer bidderAttend;

    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "minBidding_price")
    private Double minBiddingPrice;

    @Column(name = "maxBidding_price")
    private Double maxBiddingPrice;

}
