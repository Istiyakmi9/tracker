package com.bottrack.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ratinghistory")
public class RatingHistory {
    @Id
    @Column( name = "RatingHistoryId")
    Long ratingHistoryId;
    @Column( name = "UserId")
    Long userId;
    @Column( name = "Rating")
    float rating;
    @Column( name = "Comment")
    String comment;
    @Column( name = "FileId")
    int fileId;
    @Column( name = "VisitingAddress")
    String visitingAddress;
    @Column( name = "FeedbackOn")
    Date feedbackOn;
    @Column( name = "Latitude")
    String latitude;
    @Column( name = "Longitude")
    String longitude;
    @Transient
    String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getRatingHistoryId() {
        return ratingHistoryId;
    }

    public void setRatingHistoryId(Long ratingHistoryId) {
        this.ratingHistoryId = ratingHistoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getVisitingAddress() {
        return visitingAddress;
    }

    public void setVisitingAddress(String visitingAddress) {
        this.visitingAddress = visitingAddress;
    }

    public Date getFeedbackOn() {
        return feedbackOn;
    }

    public void setFeedbackOn(Date feedbackOn) {
        this.feedbackOn = feedbackOn;
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
    public RatingHistory() {}

    public RatingHistory(Long ratingHistoryId, Long userId, float rating, String comment, int fileId, String visitingAddress, Date feedbackOn, String latitude, String longitude, String filePath) {
        this.ratingHistoryId = ratingHistoryId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.fileId = fileId;
        this.visitingAddress = visitingAddress;
        this.feedbackOn = feedbackOn;
        this.latitude = latitude;
        this.longitude = longitude;
        this.filePath = filePath;
    }
}
