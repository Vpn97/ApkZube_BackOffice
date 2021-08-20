package com.apkzube.bo.web.rest.response;

import com.apkzube.bo.entity.AppMst;
import com.apkzube.bo.entity.TutorialCategoryMst;
import java.util.Date;

public class TutCategoryMstDTO {

    private Long tutCatMstId;

    private Long appId;

    private String categoryName;

    private String categoryDesc;

    private String catType;

    private String iconURL;

    private String imgURL;

    private boolean isActive;

    private Long createdBy;

    private Date createdDate;

    private Long updatedBy;

    private Date updatedDate;

    private String createdUserName;

    private String updatedUserName;

    private AppMst appMst;

    public TutCategoryMstDTO(TutorialCategoryMst categoryMst, AppMst appMst) {
        this.appMst = appMst;
        this.tutCatMstId = categoryMst.getTutCatMstId();
        this.appId = categoryMst.getAppId();
        this.categoryName = categoryMst.getCategoryName();
        this.categoryDesc = categoryMst.getCategoryDesc();
        this.catType = categoryMst.getCatType();
        this.iconURL = categoryMst.getIconURL();
        this.imgURL = categoryMst.getImgURL();
        this.isActive = categoryMst.isActive();
        this.createdBy = categoryMst.getCreatedBy();
        this.createdDate = categoryMst.getCreatedDate();
        this.updatedBy = categoryMst.getUpdatedBy();
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }

    public AppMst getAppMst() {
        return appMst;
    }

    public void setAppMst(AppMst appMst) {
        this.appMst = appMst;
    }

    public Long getTutCatMstId() {
        return tutCatMstId;
    }

    public void setTutCatMstId(Long tutCatMstId) {
        this.tutCatMstId = tutCatMstId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
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
