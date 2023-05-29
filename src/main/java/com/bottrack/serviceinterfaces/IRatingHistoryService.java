package com.bottrack.serviceinterfaces;

import com.bottrack.model.RatingHistory;
import org.springframework.web.multipart.MultipartFile;

public interface IRatingHistoryService {
    String addRatingDetailService(RatingHistory rating, MultipartFile file) throws Exception;
    RatingHistory updateRatingDetailService(RatingHistory ratingHistory, MultipartFile file, Long RatingHistoryId) throws Exception;
    RatingHistory getHistoryRatingByUserIdService(Long userId) throws Exception;
}
