package com.bottrack.controller;

import com.bottrack.model.VisitHistoryModel;
import com.bottrack.serviceinterfaces.IVisitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visithistory/")
public class VisitHIstoryController {
    @Autowired
    IVisitHistoryService visitHistoryService;
    @GetMapping("get/{userId}")
    public ResponseEntity<List<VisitHistoryModel>> get(@PathVariable long userId) {
        var response = visitHistoryService.getMonthVisitedMapHistory(userId);
        return ResponseEntity.ok(response);
    }
}
