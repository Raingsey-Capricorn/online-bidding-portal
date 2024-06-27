package com.system.bidding.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Data
@MappedSuperclass
public abstract class CommonEntity implements Serializable {

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "created_date",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "updated_date",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "disabled_date",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disabledDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "enabled_date",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enabledDate;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "removed_date",
            columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
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

    @PrePersist
    protected void prePersist() {
        if (!Objects.isNull(getCreatedBy())
                && Objects.isNull(getCreatedDate())
        ) {
            final var datetime = new Date();
            setEnabledBy(getCreatedBy());
            setEnabledDate(datetime);
            setCreatedDate(datetime);
            setIsEnabled(true);
            setStatus(true);
        }
        if (!Objects.isNull(getUpdatedBy()) && Objects.isNull(getUpdatedDate())) {
            setUpdatedDate(new Date());
        }
        if (!Objects.isNull(getIsEnabled()) && Boolean.compare(getIsEnabled(), false) == 0) {
            setDisabledDate(new Date());
            setStatus(false);
        }
    }

    @PreRemove
    protected void PreRemove() {
        if (!Objects.isNull(getRemovedBy())
                && Objects.isNull(getRemovedDate())
        ) {
            setRemovedDate(new Date());
            setStatus(false);
        }
        if (!Objects.isNull(getIsEnabled()) && Boolean.compare(getIsEnabled(), false) == 0) {
            setDisabledDate(new Date());
        }
    }

    @PreUpdate
    protected void PreUpdate() {
        if (!Objects.isNull(getUpdatedBy())
                && Objects.isNull(getUpdatedDate())
        ) {
            setUpdatedDate(new Date());
            setStatus(true);
        }
        if (!Objects.isNull(getUpdatedBy()) && Objects.isNull(getUpdatedDate())) {
            setUpdatedDate(new Date());
        }
    }


}