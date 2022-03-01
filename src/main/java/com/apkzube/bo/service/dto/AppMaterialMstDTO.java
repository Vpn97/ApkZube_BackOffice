package com.apkzube.bo.service.dto;

import com.apkzube.bo.entity.AppMaterialMst;
import java.util.Date;

public class AppMaterialMstDTO {

    private Long materialId;

    private Long appId;

    private String title;

    private String typeCode;

    private String detail;

    private String materialURL;

    private String iconURL;

    private boolean isActive;

    private String clickActionCode;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    public AppMaterialMstDTO() {}

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMaterialURL() {
        return materialURL;
    }

    public void setMaterialURL(String materialURL) {
        this.materialURL = materialURL;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getClickActionCode() {
        return clickActionCode;
    }

    public void setClickActionCode(String clickActionCode) {
        this.clickActionCode = clickActionCode;
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
