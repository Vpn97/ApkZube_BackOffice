package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "programming_lang_mst")
@Entity
public class ProgrammingLangMst {

    @Id
    @Column(name = "lang_id", nullable = false)
    private Long langId;

    @Column(name = "lang_name", nullable = false)
    private String langName;

    @Column(name = "lang_code", nullable = false)
    private String langCode;

    @Column(name = "lang_mode", nullable = false)
    private String langMode;

    @Column(name = "lang_theme")
    private String langTheme;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "icon_url")
    private String iconURL;

    @Column(name = "img_url")
    private String imgURL;

    @Column(name = "created_by", length = 8)
    @ColumnDefault("1")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
