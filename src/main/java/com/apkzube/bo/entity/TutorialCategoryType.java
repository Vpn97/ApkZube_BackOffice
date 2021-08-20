package com.apkzube.bo.entity;

import javax.persistence.*;

@Table(name = "tutorial_category_type")
@Entity
public class TutorialCategoryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_type_id", nullable = false)
    private Long catTypeId;

    @Column(name = "cat_type_code", nullable = false)
    private String catTypeCode;

    @Column(name = "type", nullable = false)
    private String type;

    public Long getCatTypeId() {
        return catTypeId;
    }

    public void setCatTypeId(Long catTypeId) {
        this.catTypeId = catTypeId;
    }

    public String getCatTypeCode() {
        return catTypeCode;
    }

    public void setCatTypeCode(String catTypeCode) {
        this.catTypeCode = catTypeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
