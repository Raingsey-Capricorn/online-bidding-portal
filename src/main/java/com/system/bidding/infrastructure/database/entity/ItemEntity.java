package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */

@Data
@Entity
@Table(name = "table_items")
@EqualsAndHashCode(callSuper = true)
public class ItemEntity extends CommonEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "price")
    private Double price;

    @Column(name = "min_bidding_price")
    private Double minBiddingPrice;

    @Column(name = "max_bidding_price")
    private Double maxBiddingPrice;

    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @OneToMany(mappedBy = "itemEntity")
    private List<BiddingHistoryEntity> biddingHistories;
}
