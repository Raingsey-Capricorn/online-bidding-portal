package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Data
@MappedSuperclass
public abstract class CommonEntity {

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "created_date")
    private Date createdDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "updated_date")
    private Date updatedDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "disabled_date")
    private Date disabledDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "enabled_date")
    private Date enabledDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "removed_date")
    private Date removedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "removed_by")
    private String removedBy;

    @Column(name = "disabled_by")
    private String disabledBy;

    @Column(name = "enabled_by")
    private String enabledBy;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "remark")
    private String remark;

}