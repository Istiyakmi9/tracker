package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.RatingHistory;
import com.bottrack.serviceinterfaces.IRatingHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ratinghistory")
public class RatingHistoryController extends BaseController{
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    IRatingHistoryService iRatingHistoryService;
    @PostMapping("/addRatingDetail")
    public ResponseEntity<ApiResponse> addRatingDetail(@RequestParam("ratingHistoryDetail") String ratingHistoryDetail,
                                                        @RequestParam("file") MultipartFile file) throws Exception {
        RatingHistory rating = objectMapper.readValue(ratingHistoryDetail, RatingHistory.class);
        var result = this.iRatingHistoryService.addRatingDetailService(rating, file);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateRatingDetail/{ratingHistoryId}")
    public ResponseEntity<ApiResponse> updateRatingDetail(@RequestParam("ratingHistory") String ratingHistory,
                                                           @RequestParam("file") MultipartFile file,
                                                           @PathVariable("ratingHistoryId") long ratingHistoryId) throws Exception {

        RatingHistory rating = objectMapper.readValue(ratingHistory, RatingHistory.class);
        var result = this.iRatingHistoryService.updateRatingDetailService(rating, file, ratingHistoryId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getRatingDetail/{userId}")
    public ResponseEntity<ApiResponse> getRatingDetail(@PathVariable("userId") Long userId) throws Exception {
        var result = this.iRatingHistoryService.getHistoryRatingByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
