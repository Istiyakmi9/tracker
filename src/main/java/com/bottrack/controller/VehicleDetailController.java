package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.VehicleDetail;
import com.bottrack.service.VehicleDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/vehicledetail")
public class VehicleDetailController extends BaseController {

    @Autowired
    VehicleDetailService vehicleDetailService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/addVehicleDetail")
    public ResponseEntity<ApiResponse> addVehicleDetail(@RequestParam("vehicleDetail") String vehicleDetail,
                                                        @RequestParam("file") MultipartFile file) throws Exception {
        VehicleDetail vehicle = objectMapper.readValue(vehicleDetail, VehicleDetail.class);
        var result = this.vehicleDetailService.addVehicleDetailService(vehicle, file);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateVehicle/{vehicleId}")
    public ResponseEntity<ApiResponse> updateVehicleDetail(@RequestParam("vehicleDetail") String vehicle,
                                                           @RequestParam("file") MultipartFile file,
                                                           @PathVariable("vehicleId") long vehicleId) throws Exception {

        VehicleDetail vehicleDetail = objectMapper.readValue(vehicle, VehicleDetail.class);
        var result = this.vehicleDetailService.updateVehicleDetailService(vehicleDetail, file, vehicleId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getVehicleByEmail/{email}")
    public ResponseEntity<ApiResponse> getVehicleByEmail(@PathVariable("email") String email) throws Exception {
        var result = this.vehicleDetailService.getVehicleByEmailService(email);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getVehicleByMobile/{mobile}")
    public ResponseEntity<ApiResponse> getVehicleByMobile(@PathVariable("mobile") String mobile) throws Exception {
        var result = this.vehicleDetailService.getVehicleByMobileService(mobile);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getVehicleByUserId/{userId}")
    public ResponseEntity<ApiResponse> getVehicleByUserId(@PathVariable("userId") Long userId) throws Exception {
        var result = this.vehicleDetailService.getVehicleByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

//    Old Code

    @PutMapping("/updateVehicleDetailByVehicleNo/{vehicleNo}")
    public ResponseEntity<ApiResponse> updateVehicleDetailByVehicleNo(@RequestBody VehicleDetail vehicleDetail, @PathVariable("vehicleNo") String vehicleNo) throws IOException {
        var result = this.vehicleDetailService.updateVehicleDetailByVehicleNoService(vehicleDetail, vehicleNo);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
    @GetMapping("/getAllVehicleDetail")
    public ResponseEntity<ApiResponse> getAllVehicleDetail(){
        var result = this.vehicleDetailService.getAllVehicleDetailService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getVehicleDetailByVehicleNo/{vehicleNo}")
    public ResponseEntity<ApiResponse> getVehicleDetailByVehicleNo(@PathVariable("vehicleNo") long vehicleNo){
        var result = this.vehicleDetailService.getVehicleDetailByVehicleNoService(vehicleNo);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteVehicleDetailByVehicleNo/{vehicleNo}")
    public ResponseEntity<ApiResponse> deleteVehicleDetailByVehicleNo(@PathVariable("vehicleNo") long vehicleNo){
        var result = this.vehicleDetailService.deleteVehicleDetailByVehicleNoService(vehicleNo);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/createOrUpdateVehicle/{userId}")
    public ResponseEntity<ApiResponse> createOrUpdateVehicleDetail(@RequestParam("vehicleDetail") String vehicle,
                                                  @RequestParam("file") MultipartFile file,
                                                  @PathVariable("userId") long userId,
                                                  @PathVariable("vehicleId") long vehicleNo) throws Exception {

        VehicleDetail vehicleDetail = objectMapper.readValue(vehicle, VehicleDetail.class);
        var result = this.vehicleDetailService.createOrUpdateVehicleDetailService(vehicleDetail, vehicleNo, userId, file);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }


}
