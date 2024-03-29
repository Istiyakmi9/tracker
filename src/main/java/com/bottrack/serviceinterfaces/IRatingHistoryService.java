package com.bottrack.serviceinterfaces;

import com.bottrack.model.RatingHistory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRatingHistoryService {
    String addRatingDetailService(RatingHistory rating) throws Exception;
    RatingHistory updateRatingDetailService(RatingHistory ratingHistory, Long RatingHistoryId) throws Exception;
    List<RatingHistory> getHistoryRatingByUserIdService(Long userId) throws Exception;
}
