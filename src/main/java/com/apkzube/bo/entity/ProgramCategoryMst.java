package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "program_category_mst")
@Entity
public class ProgramCategoryMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_cat_id", nullable = false)
    private Long programCatId;

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "cat_description")
    private String catDescription;

    @Column(name = "cat_icon_url")
    private String catIconURL;

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

    public Long getProgramCatId() {
        return programCatId;
    }

    public void setProgramCatId(Long programCatId) {
        this.programCatId = programCatId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public String getCatIconURL() {
        return catIconURL;
    }

    public void setCatIconURL(String catIconURL) {
        this.catIconURL = catIconURL;
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
