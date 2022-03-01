package com.apkzube.bo.service.dto;

import com.apkzube.bo.entity.PracticeProgramMst;
import java.util.Date;

public class PracticeProgramDTO {

    private Long programId;

    private Long appId;

    private Long catId;

    private String title;

    private String programText;

    private String outputText;

    private String programInput;

    private String explainText;

    private String programBy;

    private String programURL;

    private String programIconURL;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    public PracticeProgramDTO() {}

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProgramText() {
        return programText;
    }

    public void setProgramText(String programText) {
        this.programText = programText;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public String getProgramInput() {
        return programInput;
    }

    public void setProgramInput(String programInput) {
        this.programInput = programInput;
    }

    public String getExplainText() {
        return explainText;
    }

    public void setExplainText(String explainText) {
        this.explainText = explainText;
    }

    public String getProgramBy() {
        return programBy;
    }

    public void setProgramBy(String programBy) {
        this.programBy = programBy;
    }

    public String getProgramURL() {
        return programURL;
    }

    public void setProgramURL(String programURL) {
        this.programURL = programURL;
    }

    public String getProgramIconURL() {
        return programIconURL;
    }

    public void setProgramIconURL(String programIconURL) {
        this.programIconURL = programIconURL;
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
