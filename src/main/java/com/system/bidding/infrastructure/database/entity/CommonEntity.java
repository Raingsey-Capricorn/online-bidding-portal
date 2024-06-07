package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@MappedSuperclass
public abstract class CommonEntity {

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "disabled_date")
    private Date disabledDate;

    @Column(name = "enabled_date")
    private Date enabledDate;

    @Column(name = "removed_date")
    private String removedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "removed_by")
    private String removedBy;

    @Column(name = "disabled_by")
    private String disabledBy;

    @Column(name = "enabled_by")
    private String enabledBy;

    @Column(name = "status")
    private String status;

    @Column(name = "remark")
    private String remark;


}