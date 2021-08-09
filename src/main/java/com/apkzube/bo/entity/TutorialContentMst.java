package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "tutorial_content_mst")
@Entity
public class TutorialContentMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tut_content_id", nullable = false)
    private Long tutContentId;

    @Column(name = "tut_dtl_id")
    private Long tutDtlId;

    @Column(name = "content_type_id")
    private Long contentTypeId;

    @Column(name = "content_data")
    private String contentData;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_by", length = 8)
    @ColumnDefault("1")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by", nullable = true)
    private Long updatedBy;

    @Column(name = "updated_date", nullable = true)
    private Date updatedDate;

    public Long getTutContentId() {
        return tutContentId;
    }

    public void setTutContentId(Long tutContentId) {
        this.tutContentId = tutContentId;
    }

    public Long getTutDtlId() {
        return tutDtlId;
    }

    public void setTutDtlId(Long tutDtlId) {
        this.tutDtlId = tutDtlId;
    }

    public Long getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Long contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
