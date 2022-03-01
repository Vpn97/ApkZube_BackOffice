package com.apkzube.bo.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ProgramCategoryFormDTO {

    private Long programCatId;

    @NotNull
    private Long appId;

    @NotNull
    @Size(min = 8, max = 60)
    private String catName;

    @NotNull
    @Size(min = 8, max = 200)
    private String catDescription;

    private MultipartFile iconFile;

    @NotNull
    private boolean isActive;

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
}
