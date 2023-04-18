package com.bottrack.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "vehicledetail")
public class VehicleDetail {
    @Id
    @Column(name="VehicleId")
    Long vehicleId;
    @Column(name = "VehicleNo")
    String vehicleNo;

    @Column(name = "Make")
    String make;

    @Column(name = "Model")
    String model;

    @Column(name = "Varient")
    String varient;

    @Column(name = "VehicleType")
    String vehicleType;

    @Column(name = "Series")
    String series;
    @Column(name="UserId")
    Long userId;
    @Transient
    String filePath;

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

    @Override
    public String toString() {
        return "VehicleDetail{" +
                "vehicleId=" + vehicleId +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", varient='" + varient + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", series='" + series + '\'' +
                ", userId=" + userId +
                ", filePath='" + filePath + '\'' +
                ", adminId=" + adminId +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }

    public VehicleDetail() {
    }

    public VehicleDetail(long vehicleId, String vehicleNo, String make, String model, String varient, String vehicleType, String series, long userId, String filePath, long adminId, Long createdBy, Long updatedBy, Date createdOn, Date updatedOn) {
        this.vehicleId = vehicleId;
        this.vehicleNo = vehicleNo;
        this.make = make;
        this.model = model;
        this.varient = varient;
        this.vehicleType = vehicleType;
        this.series = series;
        this.userId = userId;
        this.filePath = filePath;
        this.adminId = adminId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
}



