package com.apkzube.bo.entity;

import java.sql.Date;
import javax.persistence.*;

@Table(name = "app_mst")
@Entity
public class AppMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id", nullable = false)
    private Long appId;

    @Column(name = "langId", nullable = false)
    private Long langId;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_code")
    private String appCode;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "app_price")
    private String appPrice;

    @Column(name = "app_size")
    private String appSize;

    @Column(name = "app_link")
    private String appLink;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "developer_name")
    private String developerName;

    @Column(name = "privacy_policy_url")
    private String privacyPolicyUrl;

    @Column(name = "blog_base_url")
    private String blogBaseUrl;

    @Column(name = "created_date")
    private Date createdDate;

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }

    public String getBlogBaseUrl() {
        return blogBaseUrl;
    }

    public void setBlogBaseUrl(String blogBaseUrl) {
        this.blogBaseUrl = blogBaseUrl;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppPrice() {
        return appPrice;
    }

    public void setAppPrice(String appPrice) {
        this.appPrice = appPrice;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
