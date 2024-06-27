package com.system.bidding.infrastructure.database.entity;

import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import com.system.bidding.infrastructure.utilities.BidderType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "table_bidders")
@EqualsAndHashCode(callSuper = true)
public class BidderEntity extends CommonEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PrimaryKeyJoinColumn(name = "user_id")
    private Long userId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BidderType type;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "login_time",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    @Column(name = "session_start_date",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionStartDate;

    @Column(name = "session_duration")
    private Integer sessionDuration;

    @Column(name = "credential")
    private String credential;

    @Column(name = "authorization_server")
    private String authorizationServer;

    @Column(name = "authorization_provider")
    @Enumerated(EnumType.STRING)
    private SecurityConstant.AuthorizationProvider authorizationProvider;

    @Transient
    @OneToMany
    private List<BiddingHistoryEntity> biddingHistories = new ArrayList<>();

    /**
     * @param id       : bidder's ID
     * @param userId   : user's ID
     * @param type     : bidder type
     * @param userName : user Name
     */
    public BidderEntity(
            final Optional<Long> id,
            final Optional<Long> userId,
            final Optional<BidderType> type,
            final Optional<String> userName) {
        this.id = id.orElse(null);
        this.userId = userId.orElse(null);
        this.type = type.orElse(null);
        this.userName = userName.orElse(null);
    }
}
