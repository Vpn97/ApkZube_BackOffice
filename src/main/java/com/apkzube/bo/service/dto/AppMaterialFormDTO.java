package com.apkzube.bo.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class AppMaterialFormDTO {

    private Long materialId;

    @NotNull
    private Long appId;

    @NotNull
    @Size(min = 8, max = 60)
    private String title;

    @NotNull
    private String typeCode;

    @NotNull
    @Size(min = 8, max = 200)
    private String detail;

    @NotNull
    private String materialURL;

    private MultipartFile iconFile;

    @NotNull
    private boolean isActive;

    @NotNull
    @Size(min = 3, max = 60)
    private String clickActionCode;

    public AppMaterialFormDTO() {}

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

    public MultipartFile getIconFile() {
        return iconFile;
    }

    public void setIconFile(MultipartFile iconFile) {
        this.iconFile = iconFile;
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
}
