package com.apkzube.bo.service.dto;

import com.apkzube.bo.entity.ProgramCategoryMst;
import java.util.Date;

public class ProgramCategoryDTO {

    private Long programCatId;

    private Long appId;

    private String catName;

    private String catDescription;

    private String catIconURL;

    private boolean isActive;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private int totalExamples;

    public ProgramCategoryDTO() {}

    public ProgramCategoryDTO(ProgramCategoryMst categoryMst) {
        this.programCatId = categoryMst.getProgramCatId();
        this.appId = categoryMst.getAppId();
        this.catDescription = categoryMst.getCatDescription();
        this.catName = categoryMst.getCatName();
        this.createdDate = categoryMst.getCreatedDate();
        this.updatedDate = categoryMst.getUpdatedDate();
        this.isActive = categoryMst.isActive();
    }

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

    public int getTotalExamples() {
        return totalExamples;
    }

    public void setTotalExamples(int totalExamples) {
        this.totalExamples = totalExamples;
    }
}
