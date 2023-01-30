package com.bottrack.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    long userId;

    @Column(name = "UserName")
    String userName;

    @Column(name = "Mobile")
    String mobile;

    @Column(name = "Email")
    String email;

    @Column(name = "Password")
    String password;

    @Column(name = "RoleId")
    int roleId;

    @Transient
    long adminId;

    @Column(name = "CreatedBy")
    Long createdBy;

    @Column(name = "UpdatedBy")
    Long updatedBy;

    @Column(name = "CreatedOn")
    Date createdOn;

    @Column(name = "UpdatedOn")
    Date updatedOn;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Login(long userId, String userName, String mobile, String email, String password, int roleId, long adminId, Long createdBy, Long updatedBy, Date createdOn, Date updatedOn) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.adminId = adminId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Login() {
    }

    @Override
    public String toString() {
        return "Login{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", adminId=" + adminId +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
