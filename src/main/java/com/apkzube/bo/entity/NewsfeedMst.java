package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "newsfeed_mst")
@Entity
public class NewsfeedMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsfeed_id", nullable = false)
    private Long newsfeedId;

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "newsfeed_title")
    private String newsfeedTitle;

    @Column(name = "newsfeed_sort_disc")
    private String newsfeedSortDisc;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_only_img")
    private boolean isOnlyImg;

    @Column(name = "is_app_intent")
    private boolean isAppIntent;

    @Column(name = "click_action_code")
    private String clickActionCode;

    @Column(name = "url")
    private String url;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "created_by", length = 8)
    @ColumnDefault("1")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by", nullable = true)
    private Long updatedBy;

    @Column(name = "updated_date", nullable = true)
    private Date updatedDate;

    public Long getNewsfeedId() {
        return newsfeedId;
    }

    public void setNewsfeedId(Long newsfeedId) {
        this.newsfeedId = newsfeedId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getNewsfeedTitle() {
        return newsfeedTitle;
    }

    public void setNewsfeedTitle(String newsfeedTitle) {
        this.newsfeedTitle = newsfeedTitle;
    }

    public String getNewsfeedSortDisc() {
        return newsfeedSortDisc;
    }

    public void setNewsfeedSortDisc(String newsfeedSortDisc) {
        this.newsfeedSortDisc = newsfeedSortDisc;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOnlyImg() {
        return isOnlyImg;
    }

    public void setOnlyImg(boolean onlyImg) {
        isOnlyImg = onlyImg;
    }

    public boolean isAppIntent() {
        return isAppIntent;
    }

    public void setAppIntent(boolean appIntent) {
        isAppIntent = appIntent;
    }

    public String getClickActionCode() {
        return clickActionCode;
    }

    public void setClickActionCode(String clickActionCode) {
        this.clickActionCode = clickActionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Date getUpdateDate() {
        return updatedDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updatedDate = updateDate;
    }
}
