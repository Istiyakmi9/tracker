package com.bottrack.service;

import com.bottrack.model.VehicleDetail;
import com.bottrack.repository.VehicleDetailRepository;
import com.bottrack.serviceinterfaces.IVehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class VehicleDetailService implements IVehicleDetailService {

    @Autowired
    VehicleDetailRepository vehicleDetailRepository;


    public String addVehicleDetailService(VehicleDetail vehicleDetail) {
        var result = this.vehicleDetailRepository.addVehicleDetailRepository(vehicleDetail);
        return result;
    }

    public String updateVehicleDetailByVehicleNoService(VehicleDetail vehicleDetail, long vehicleNo) throws IOException {
        var result = "";
        if (vehicleNo > 0){
            result = this.vehicleDetailRepository.updateVehicleDetailByVehicleNoRepository(vehicleDetail, vehicleNo);
            if (result == null || result == "")
                throw new IOException("Unable to update VehicleDetail");
        }
        return result;
    }

    public ArrayList<VehicleDetail> getAllVehicleDetailService() {
        var result = this.vehicleDetailRepository.getAllVehicleDetailRepository();
        return result;
    }

    public ArrayList<VehicleDetail> getVehicleDetailByVehicleNoService(long vehicleNo) {
        var result = this.vehicleDetailRepository.getVehicleDetailByVehicleNoRepository(vehicleNo);
        return result;

    }

    public String deleteVehicleDetailByVehicleNoService(long vehicleNo) {
       var result = this.vehicleDetailRepository.deleteVehicleDetailByVehicleNoRepository(vehicleNo);
       return result;
    }
}
