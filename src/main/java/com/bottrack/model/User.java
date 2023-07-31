package com.bottrack.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "UserId")
    Long userId;

    @Column(name = "FirstName")
    String firstName;

    @Column(name = "LastName")
    String lastName;


    @Column(name = "DOB")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date dob;

    @Column(name = "Mobile")
    String mobile;

    @Column(name = "Email")
    String email;

    @Column(name = "FirstAddress")
    String firstAddress;

    @Column(name = "SecondAddress")
    String secondAddress;

    @Column(name = "State")
    String state;

    @Column(name = "City")
    String city;

    @Column(name = "Country")
    String country;

    @Transient
    long adminId;

    @Column(name = "CreatedBy")
    Long createdBy;

    @Column(name = "UpdatedBy")
    Long updatedBy;

    @Column(name = "CreatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdOn;

    @Column(name = "UpdatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedOn;

    @Transient
    String filePath;
    @Transient
    String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public String getFirstAddress() {
        return firstAddress;
    }

    public void setFirstAddress(String firstAddress) {
        this.firstAddress = firstAddress;
    }

    public String getSecondAddress() {
        return secondAddress;
    }

    public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", firstAddress='" + firstAddress + '\'' +
                ", secondAddress='" + secondAddress + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", adminId=" + adminId +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", filePath='" + filePath + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {}

    public User(Long userId, String firstName, String lastName, Date dob, String mobile, String email, String firstAddress, String secondAddress, String state, String city, String country, long adminId, Long createdBy, Long updatedBy, Date createdOn, Date updatedOn, String filePath, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.mobile = mobile;
        this.email = email;
        this.firstAddress = firstAddress;
        this.secondAddress = secondAddress;
        this.state = state;
        this.city = city;
        this.country = country;
        this.adminId = adminId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.filePath = filePath;
        this.password = password;
    }
}
