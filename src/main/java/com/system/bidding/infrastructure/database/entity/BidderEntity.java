package com.system.bidding.infrastructure.database.entity;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.utilities.BidderType;
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
@Table(name = "table_bidders")
@EqualsAndHashCode(callSuper = true)
public class BidderEntity extends CommonEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authorizationProvider")
    private BidderType type;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "credential")
    private String credential;

    @Column(name = "authorization_server")
    private String authorizationServer;

    @Column(name = "authorization_type")
    @Enumerated(EnumType.STRING)
    private SecurityConstant.AuthorizationProvider authorizationProvider;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "bidding_history_id")
    private BiddingHistoryEntity biddingHistoryEntity;

}
