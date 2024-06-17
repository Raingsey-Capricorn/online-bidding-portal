package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
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
    private Long clientId;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "price")
    private Double price;

    @Column(name = "min_bidding_price")
    private Double minBiddingPrice;

    @Column(name = "max_bidding_price")
    private Double maxBiddingPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "entry_date", columnDefinition = "datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entryDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date", columnDefinition = "datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiryDate;

    @OneToMany(mappedBy = "itemEntity")
    private List<BiddingHistoryEntity> biddingHistories;

    @Override
    protected void prePersist() {

        if (!Objects.isNull(this.getClientId())) {
            this.setCreatedBy(clientId.toString());
            this.setEnabledBy(clientId.toString());
            this.setMinBiddingPrice(0D);
            this.setMaxBiddingPrice(0D);
            this.setEntryDate(new Timestamp(new Date().getTime()));
            this.setEnabledDate(new Timestamp(new Date().getTime()));
            this.setStatus(true);
            this.setRemark(Objects.isNull(this.getRemark()) ? "Create through HTTP by " + clientId : this.getRemark());
        }
    }
}
