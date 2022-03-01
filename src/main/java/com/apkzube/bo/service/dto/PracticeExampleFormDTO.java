package com.apkzube.bo.service.dto;

import javax.validation.constraints.NotNull;

public class PracticeExampleFormDTO {

    private Long programId;

    private Long appId;

    @NotNull
    private Long catId;

    @NotNull
    private String title;

    @NotNull
    private String programText;

    @NotNull
    private String outputText;

    private String programInput;

    @NotNull
    private String explainText;

    private String programBy;

    private String programURL;

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
}
