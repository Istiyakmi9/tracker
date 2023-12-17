package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.FilterModel;
import com.bottrack.model.VisitHistoryModel;
import com.bottrack.serviceinterfaces.IVisitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visithistory/")
public class VisitHIstoryController {
    @Autowired
    IVisitHistoryService visitHistoryService;

    @GetMapping("get/{userId}")
    public ResponseEntity<ApiResponse> get(@PathVariable long userId) {
        var response = visitHistoryService.getMonthVisitedMapHistory(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(response);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Successfull");
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("get")
    public ResponseEntity<ApiResponse> get(@RequestBody FilterModel filterModel) {
        var response = visitHistoryService.getMonthVisitedMapHistory(filterModel);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(response);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Successfull");
        return ResponseEntity.ok(apiResponse);
    }
}
