package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "action_code_mst")
@Entity
public class ActionCodeMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_code_id", nullable = false)
    private Long actionCodeId;

    @Column(name = "click_action_code", unique = true)
    private String actionCode;

    @Column(name = "action_desc")
    private String actionDesc;

    @Column(name = "created_by", length = 8)
    @ColumnDefault("1")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Long getActionCodeId() {
        return actionCodeId;
    }

    public void setAction_code_id(Long actionCodeId) {
        this.actionCodeId = actionCodeId;
    }

    public void setActionCodeId(Long actionCodeId) {
        this.actionCodeId = actionCodeId;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
