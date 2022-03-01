package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "app_material_type")
@Entity
public class AppMaterialType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_type_id", nullable = false)
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "created_date")
    private Date createdDate;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
