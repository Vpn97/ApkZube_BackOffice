package com.apkzube.bo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "admin_mst")
@Entity
public class AdminMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id", nullable = false)
    private Long adminId;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "admin_role")
    private String adminRole;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_num")
    private String mobileNum;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "created_date")
    private Date createdDate;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNum;
    }

    public void setMobileNumber(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
