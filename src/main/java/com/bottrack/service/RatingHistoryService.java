package com.bottrack.service;

import com.bottrack.filehandler.FileManager;
import com.bottrack.model.RatingHistory;
import com.bottrack.repository.RatingHistoryRepository;
import com.bottrack.repositorymodel.FileDetail;
import com.bottrack.serviceinterfaces.IRatingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.*;

@Service
public class RatingHistoryService implements IRatingHistoryService {
    @Autowired
    RatingHistoryRepository ratingHistoryRepository;
    @Autowired
    FileManager fileManager;
    @Autowired
    FileService fileService;

    @Transactional(rollbackFor = Exception.class)

    public String addRatingDetailService(RatingHistory rating) throws Exception {
        if (rating.getUserId() == 0)
            throw new Exception("Invalid userid. Please login again");

        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        Optional<RatingHistory> ratingDetail = Optional.ofNullable(this.ratingHistoryRepository.getLastRatingHistory());
        if (ratingDetail.isEmpty())
            rating.setRatingHistoryId(1L);
        else
            rating.setRatingHistoryId(ratingDetail.get().getRatingHistoryId() + 1);

        rating.setFeedbackOn(date);
        this.ratingHistoryRepository.save(rating);
        return "inserted";
    }

    @Transactional(rollbackFor = Exception.class)
    public RatingHistory updateRatingDetailService(RatingHistory ratingHistory, Long RatingHistoryId) throws Exception {
        if (RatingHistoryId == 0)
            throw new Exception("Invalid rating history id");

        if (ratingHistory.getUserId() <= 0)
            throw new Exception("Invalid userid. Please login again");

        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        Optional<RatingHistory> existrating = this.ratingHistoryRepository.findById(RatingHistoryId);
        if (existrating.isEmpty())
            throw new Exception("rating details are not found");

        RatingHistory rating = existrating.get();
        rating.setRating(ratingHistory.getRating());
        rating.setComment(ratingHistory.getComment());
        rating.setVisitingAddress(ratingHistory.getVisitingAddress());
        rating.setFeedbackOn(date);
        rating.setLatitude(ratingHistory.getLatitude());
        rating.setLongitude(ratingHistory.getLongitude());
        this.ratingHistoryRepository.save(rating);
        return rating;
    }

    public List<RatingHistory> getHistoryRatingByUserIdService(Long userId) throws Exception {
        if (userId <= 0)
            throw new Exception("Invalid user selected. Please login again");

        List<RatingHistory> ratingHistories = new ArrayList<>();

        try {
            ratingHistories = this.ratingHistoryRepository.findByUserId(userId);
        } catch (Exception e) {
            throw e;
        }

        return ratingHistories;
    }

    private void saveUpdateFileDetail(RatingHistory result, MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            String oldFilePath = "";
            FileDetail existingFileDetail = fileService.getVehicleFileDetail(result.getUserId());
            if (existingFileDetail != null) {
                oldFilePath = existingFileDetail.getFileName() + "." + existingFileDetail.getExtension();
            }

            FileDetail fileDetail = fileManager.uploadFile(file, result.getRatingHistoryId(), "rating_" + new Date().getTime(), oldFilePath);
            if (fileDetail != null) {
                if (existingFileDetail != null) {
                    existingFileDetail.setFileSize((fileDetail.getFileSize()));
                    existingFileDetail.setFileName((fileDetail.getFileName()));
                    existingFileDetail.setFilePath((fileDetail.getFilePath()));
                    existingFileDetail.setExtension((fileDetail.getExtension()));
                } else {
                    existingFileDetail = fileDetail;
                }

                existingFileDetail.setUserId((result.getUserId()));
                var uploadedFile = fileService.addOrUpdateFileDetail(existingFileDetail, "rating%");
                result.setFilePath(Paths.get(existingFileDetail.getFilePath(), existingFileDetail.getFileName() + "." + existingFileDetail.getExtension()).toString());
                result.setFileId(uploadedFile.getFileDetailId());
            }
        }
    }
}
