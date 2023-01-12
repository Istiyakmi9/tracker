package com.bottrack.controller;

import com.bottrack.model.VehicleDetail;
import com.bottrack.repositorymodel.ResponseModal;
import com.bottrack.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/vehicledetail")
public class VehicleDetailController extends BaseController {

    @Autowired
    VehicleDetailService vehicleDetailService;

    @PostMapping("/addVehicleDetail")
    public ResponseModal addVehicleDetail(@RequestBody VehicleDetail vehicleDetail){
        var result = this.vehicleDetailService.addVehicleDetailService(vehicleDetail);
        return BuildOk(result);
    }

    @PutMapping("/updateVehicleDetailByVehicleNo/{vehicleNo}")
    public ResponseModal updateVehicleDetailByVehicleNo(@RequestBody VehicleDetail vehicleDetail, @PathVariable("vehicleNo") long vehicleNo) throws IOException {
        var result = this.vehicleDetailService.updateVehicleDetailByVehicleNoService(vehicleDetail, vehicleNo);
        return BuildOk(result);
    }

    @GetMapping("/getAllVehicleDetail")
    public ResponseModal getAllVehicleDetail(){
        var result = this.vehicleDetailService.getAllVehicleDetailService();
        return BuildOk(result);
    }

    @GetMapping("/getVehicleDetailByVehicleNo/{vehicleNo}")
    public ResponseModal getVehicleDetailByVehicleNo(@PathVariable("vehicleNo") long vehicleNo){
        var result = this.vehicleDetailService.getVehicleDetailByVehicleNoService(vehicleNo);
        return BuildOk(result);
    }

    @DeleteMapping("/deleteVehicleDetailByVehicleNo/{vehicleNo}")
    public ResponseModal deleteVehicleDetailByVehicleNo(@PathVariable("vehicleNo") long vehicleNo){
        var result = this.vehicleDetailService.deleteVehicleDetailByVehicleNoService(vehicleNo);
        return BuildOk(result);
    }

}
