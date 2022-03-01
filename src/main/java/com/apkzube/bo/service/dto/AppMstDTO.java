package com.apkzube.bo.service.dto;

import java.sql.Date;

public class AppMstDTO {

    private Long appId;

    private Long langId;

    private String appName;

    private String appCode;

    private String packageName;

    private String appPrice;

    private String appSize;

    private String appLink;

    private String iconUrl;

    private String developerName;

    private String blogBaseUrl;

    private String privacyPolicyUrl;

    private Date createdDate;

    private ProgrammingLangDTO programmingLangDTO;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
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

    public ProgrammingLangDTO getProgrammingLangDTO() {
        return programmingLangDTO;
    }

    public void setProgrammingLangDTO(ProgrammingLangDTO programmingLangDTO) {
        this.programmingLangDTO = programmingLangDTO;
    }

    public String getBlogBaseUrl() {
        return blogBaseUrl;
    }

    public void setBlogBaseUrl(String blogBaseUrl) {
        this.blogBaseUrl = blogBaseUrl;
    }
}
