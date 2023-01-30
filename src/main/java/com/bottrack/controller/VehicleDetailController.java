package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import com.bottrack.model.VehicleDetail;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/vehicledetail")
public class VehicleDetailController extends BaseController {

    @Autowired
    VehicleDetailService vehicleDetailService;

    @PostMapping("/addVehicleDetail")
    public ResponseEntity<ApiResponse> addVehicleDetail(@RequestBody VehicleDetail vehicleDetail){
        var result = this.vehicleDetailService.addVehicleDetailService(vehicleDetail);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateVehicleDetailByVehicleNo/{vehicleNo}")
    public ResponseEntity<ApiResponse> updateVehicleDetailByVehicleNo(@RequestBody VehicleDetail vehicleDetail, @PathVariable("vehicleNo") long vehicleNo) throws IOException {
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

}
