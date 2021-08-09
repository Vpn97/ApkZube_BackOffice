package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "tutorial_mst")
@Entity
public class TutorialMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tut_mst_id ", nullable = false)
    private Long tutMstId;

    @Column(name = "tut_cat_mst_id  ")
    private Long tutCatMstId;

    @Column(name = "tutorial_title")
    private String title;

    @Column(name = "tutorial_desc")
    private String description;

    @Column(name = "icon_url")
    private String iconURL;

    @Column(name = "img_url")
    private String imgURL;

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

    public Long getTutMstId() {
        return tutMstId;
    }

    public void setTutMstId(Long tutMstId) {
        this.tutMstId = tutMstId;
    }

    public Long getTutCatMstId() {
        return tutCatMstId;
    }

    public void setTutCatMstId(Long tutCatMstId) {
        this.tutCatMstId = tutCatMstId;
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

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
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
