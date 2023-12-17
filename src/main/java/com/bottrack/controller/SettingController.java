package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.Setting;
import com.bottrack.serviceinterfaces.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setting")
public class SettingController extends BaseController{
    @Autowired
    ISettingService iSettingService;
    @PostMapping("/addSettingDetail")
    public ResponseEntity<ApiResponse> addSettingDetail(@RequestBody Setting setting) throws Exception {
        var result = this.iSettingService.addSettingDetailService(setting);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateSettingDetail/{settingId}")
    public ResponseEntity<ApiResponse> updateSettingDetail(@PathVariable("settingId") Long settingId,
                                                           @RequestBody Setting setting) throws Exception {
        var result = this.iSettingService.updateSettingDetailService(settingId, setting);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
    @GetMapping("/getSettingByUserId/{userId}")
    public ResponseEntity<ApiResponse> getSettingByUserId(@PathVariable("userId") int userId) throws Exception {
        var result = this.iSettingService.getSettingByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

}
