package com.itsj.studentmanager.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
    @CreatedBy
    protected U created_by;

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date created_date;

    @LastModifiedBy
    protected U last_modified_by;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date last_modified_date;

    public U getCreated_by() {
        return created_by;
    }

    public void setCreated_by(U created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public U getLast_modified_by() {
        return last_modified_by;
    }

    public void setLast_modified_by(U last_modified_by) {
        this.last_modified_by = last_modified_by;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
}
