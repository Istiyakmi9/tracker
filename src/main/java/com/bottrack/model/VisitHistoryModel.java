package com.bottrack.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "visithistory")
public class VisitHistoryModel {
    @Column(name = "VisitHistoryId")
    @Id
    Long visitHistoryId;

    @Column(name="UserId")
    Long userId;

    @Column(name = "Latitude")
    String latitude;

    @Column(name = "Longitude")
    String longitude;

    @Column(name = "PlaceName")
    String placeName;

    @Column(name = "FullAddress")
    String fullAddress;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "VisitedOn")
    Date visitedOn;

    @Override
    public String toString() {
        return "VisitHistoryModel{" +
                "visitHistoryId=" + visitHistoryId +
                ", userId=" + userId +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", placeName='" + placeName + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", visitedOn=" + visitedOn +
                '}';
    }

    public VisitHistoryModel() {}

    public VisitHistoryModel(long visitHistoryId, long userId, String latitude, String longitude, String placeName, String fullAddress, Date visitedOn) {
        this.visitHistoryId = visitHistoryId;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeName = placeName;
        this.fullAddress = fullAddress;
        this.visitedOn = visitedOn;
    }

    public long getVisitHistoryId() {
        return visitHistoryId;
    }

    public void setVisitHistoryId(long visitHistoryId) {
        this.visitHistoryId = visitHistoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Date getVisitedOn() {
        return visitedOn;
    }

    public void setVisitedOn(Date visitedOn) {
        this.visitedOn = visitedOn;
    }
}
