package com.bottrack.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vehicledetail")
public class VehicleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleNo")
    long vehicleNo;

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

    public long getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(long vehicleNo) {
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

    public VehicleDetail(long vehicleNo, String make, String model, String varient, String vehicleType, String series, long adminId, Long createdBy, Long updatedBy) {
        this.vehicleNo = vehicleNo;
        this.make = make;
        this.model = model;
        this.varient = varient;
        this.vehicleType = vehicleType;
        this.series = series;
        this.adminId = adminId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public VehicleDetail() {
    }

    @Override
    public String toString() {
        return "VehicleDetail{" +
                "vehicleNo=" + vehicleNo +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", varient='" + varient + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", series='" + series + '\'' +
                ", adminId=" + adminId +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
