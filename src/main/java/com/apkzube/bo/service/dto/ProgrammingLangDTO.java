package com.apkzube.bo.service.dto;

import java.util.Date;
import javax.persistence.Column;
import org.hibernate.annotations.ColumnDefault;

public class ProgrammingLangDTO {

    private Long langId;

    private String langName;

    private String langCode;

    private String langMode;

    private String langTheme;

    private String mimeType;

    private String iconURL;

    private String imgURL;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getLangMode() {
        return langMode;
    }

    public void setLangMode(String langMode) {
        this.langMode = langMode;
    }

    public String getLangTheme() {
        return langTheme;
    }

    public void setLangTheme(String langTheme) {
        this.langTheme = langTheme;
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
