package com.bottrack.serviceinterfaces;

import com.bottrack.model.VehicleDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IVehicleDetailService {

    public VehicleDetail addVehicleDetailService(VehicleDetail vehicleDetail, MultipartFile file) throws Exception;
    public Optional<VehicleDetail> getVehicleByUserIdService(Long userId) throws Exception;
    public VehicleDetail updateVehicleDetailService(VehicleDetail vehicleDetail, MultipartFile file, Long vehicleId) throws Exception;
    public Optional<VehicleDetail> getVehicleByMobileService(String mobile) throws Exception;
    public Optional<VehicleDetail> getVehicleByEmailService(String email) throws Exception;

//    Old code
    public ResponseEntity<Object> updateVehicleDetailByVehicleNoService(VehicleDetail vehicleDetail, String vehicleNo) throws IOException;
    public VehicleDetail createOrUpdateVehicleDetailService(VehicleDetail vehicleDetail,
                                                      long vehicleNo,
                                                      long userId,
                                                      MultipartFile file
    ) throws Exception;
    public List<VehicleDetail> getAllVehicleDetailService();
    public Optional<VehicleDetail> getVehicleDetailByVehicleNoService(long vehicleNo);
    public String deleteVehicleDetailByVehicleNoService(long vehicleNo);
}
