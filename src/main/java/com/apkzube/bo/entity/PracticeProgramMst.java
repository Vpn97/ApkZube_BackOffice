package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "practice_program_mst")
@Entity
public class PracticeProgramMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id", nullable = false)
    private Long programId;

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "program_cat_id ")
    private Long catId;

    @Column(name = "program_title")
    private String title;

    @Column(name = "program_text")
    private String programText;

    @Column(name = "program_output_text")
    private String outputText;

    @Column(name = "program_input")
    private String programInput;

    @Column(name = "program_explain_text")
    private String explainText;

    @Column(name = "program_by")
    private String programBy;

    @Column(name = "program_url")
    private String programURL;

    @Column(name = "program_icon", nullable = true)
    private String programIconURL;

    @Column(name = "created_by", length = 8)
    @ColumnDefault("1")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

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

    public void setUpdateDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
