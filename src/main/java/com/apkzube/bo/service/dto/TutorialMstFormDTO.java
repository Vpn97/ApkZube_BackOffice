package com.apkzube.bo.service.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class TutorialMstFormDTO {

    private Long tutMstId;

    @NotNull
    private Long tutCatMstId;

    @NotBlank(message = "title should not be empty")
    private String title;

    @NotEmpty(message = "description should not be empty")
    private String description;

    private MultipartFile imgFile;

    private String imgURL;

    private boolean isActive;

    private Long createdBy;

    private Date createdDate;

    private Long updatedBy;

    private Date updatedDate;

    public Long getTutMstId() {
        return tutMstId;
    }

    public void setTutMstId(Long tutMstId) {
        this.tutMstId = tutMstId;
    }

    public Long getTutCatMstId() {
        return tutCatMstId;
    }

    public void setTutCatMstId(Long tutCatMstId) {
        this.tutCatMstId = tutCatMstId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
