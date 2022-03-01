package com.apkzube.bo.service.dto;

import java.util.Date;
import javax.persistence.Column;
import org.hibernate.annotations.ColumnDefault;

public class TutorialDtlDTO {

    private Long tutDtlId;

    private Long tutMstId;

    private String title;

    private String description;

    private boolean isActive;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    public Long getTutDtlId() {
        return tutDtlId;
    }

    public void setTutDtlId(Long tutDtlId) {
        this.tutDtlId = tutDtlId;
    }

    public Long getTutMstId() {
        return tutMstId;
    }

    public void setTutMstId(Long tutMstId) {
        this.tutMstId = tutMstId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
