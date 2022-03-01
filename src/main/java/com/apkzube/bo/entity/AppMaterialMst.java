package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "app_material_mst")
@Entity
public class AppMaterialMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_mst_id", nullable = false)
    private Long materialId;

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "material_title")
    private String title;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "material_detail")
    private String detail;

    @Column(name = "material_url")
    private String materialURL;

    @Column(name = "icon_url")
    private String iconURL;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "click_action_code")
    private String clickActionCode;

    @Column(name = "created_by", length = 8)
    @ColumnDefault("1")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by", nullable = true)
    private Long updatedBy;

    @Column(name = "updated_date", nullable = true)
    private Date updatedDate;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
