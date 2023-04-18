package com.bottrack.service;

import com.bottrack.filehandler.FileManager;
import com.bottrack.model.User;
import com.bottrack.model.VehicleDetail;
import com.bottrack.repository.UserRepository;
import com.bottrack.repository.VehicleDetailRepository;
import com.bottrack.repositorymodel.FileDetail;
import com.bottrack.serviceinterfaces.IVehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleDetailService implements IVehicleDetailService {

    @Autowired
    VehicleDetailRepository vehicleDetailRepository;
    @Autowired
    FileManager fileManager;
    @Autowired
    FileService fileService;
    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public VehicleDetail addVehicleDetailService(VehicleDetail vehicleDetail, MultipartFile file) throws Exception {
        if (vehicleDetail.getVehicleId() != 0)
            throw new Exception("VehicleId already exist");

        if (vehicleDetail.getUserId() <= 0)
            throw new Exception("Invalid userid. Please login again");

        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        Optional<VehicleDetail> lastVehicle = Optional.ofNullable(this.vehicleDetailRepository.getLastVehicle());
        if (lastVehicle.isEmpty())
            vehicleDetail.setVehicleId(1L);
        else
            vehicleDetail.setVehicleId(lastVehicle.get().getVehicleId()+1);

        vehicleDetail.setCreatedOn(date);
        vehicleDetail.setCreatedBy(vehicleDetail.getUserId());
        var result = this.vehicleDetailRepository.save(vehicleDetail);
        if (result == null)
            throw new Exception("Fail to insert vehicle detail. Please contact to admin");

        saveUpdateFileDetail(result, file);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public VehicleDetail updateVehicleDetailService(VehicleDetail vehicleDetail, MultipartFile file, Long vehicleId) throws Exception {
        if (vehicleId <= 0)
            throw new Exception("VehicleId already exist");

        if (vehicleDetail.getUserId() <= 0)
            throw new Exception("Invalid userid. Please login again");

        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        Optional<VehicleDetail> existVehicle = this.vehicleDetailRepository.findById(vehicleId);
        if (existVehicle.isEmpty())
            throw new Exception("Vechile detail not found");

        VehicleDetail vehicle = existVehicle.get();
        vehicle.setVehicleNo(vehicleDetail.getVehicleNo());
        vehicle.setMake(vehicleDetail.getMake());
        vehicle.setModel(vehicleDetail.getModel());
        vehicle.setVarient(vehicleDetail.getVarient());
        vehicle.setVehicleType(vehicleDetail.getVehicleType());
        vehicle.setSeries(vehicleDetail.getSeries());
        vehicleDetail.setUpdatedOn(date);
        vehicleDetail.setUpdatedBy(vehicleDetail.getUserId());
        var result = this.vehicleDetailRepository.save(vehicleDetail);
        if (result == null)
            throw new Exception("Fail to insert vehicle detail. Please contact to admin");

        saveUpdateFileDetail(result, file);
        return result;
    }

    private void saveUpdateFileDetail(VehicleDetail result, MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            String oldFilePath = "";
            FileDetail existingFileDetail = fileService.getVehicleFileDetail(result.getUserId());
            if (existingFileDetail != null) {
                oldFilePath = existingFileDetail.getFileName() + "." + existingFileDetail.getExtension();
            }

            FileDetail fileDetail = fileManager.uploadFile(file, result.getVehicleId(), "vehicle_" + new Date().getTime(), oldFilePath);
            if (fileDetail != null) {
                if (existingFileDetail != null) {
                    existingFileDetail.setFileSize((fileDetail.getFileSize()));
                    existingFileDetail.setFileName((fileDetail.getFileName()));
                    existingFileDetail.setFilePath((fileDetail.getFilePath()));
                    existingFileDetail.setExtension((fileDetail.getExtension()));
                } else {
                    existingFileDetail = fileDetail;
                }

                existingFileDetail.setUserId((result.getVehicleId()));
                fileService.addOrUpdateFileDetail(existingFileDetail);
                result.setFilePath(Paths.get(existingFileDetail.getFilePath(), existingFileDetail.getFileName()+ "."+ existingFileDetail.getExtension()).toString());
            }
        }
    }

    public Optional<VehicleDetail> getVehicleByUserIdService(Long userId) throws Exception {
        if (userId <= 0)
            throw new Exception("Invalid user selected. Please login again");

        var result = this.vehicleDetailRepository.findById(userId);
        return result;
    }

    public Optional<VehicleDetail> getVehicleByEmailService(String email) throws Exception {
        if (email == null || email.isEmpty())
            throw new Exception("Invalid user selected. Please login again");

        User user = this.userRepository.getUserByEmail(email);
        if (user == null)
            throw new Exception("Invalid email id. Please enter a valid email");

        var result = this.vehicleDetailRepository.findById(user.getUserId());
        return result;
    }

    public Optional<VehicleDetail> getVehicleByMobileService(String mobile) throws Exception {
        if (mobile == null || mobile.isEmpty())
            throw new Exception("Invalid user selected. Please login again");

        User user = this.userRepository.getByUserMobile(mobile);
        if (user == null)
            throw new Exception("Invalid mobile. Please enter a valid mobile number");

        var result = this.vehicleDetailRepository.findById(user.getUserId());
        return result;
    }

//Old Code----------------------------------------------
    public ResponseEntity<Object> updateVehicleDetailByVehicleNoService(VehicleDetail vehicleDetail, String vehicleNo) throws IOException {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        vehicleDetail.setCreatedOn(date);
//        Optional<VehicleDetail> result = this.vehicleDetailRepository.findById(vehicleNo);
//        if (result.isEmpty())
//            return ResponseEntity.notFound().build();

        vehicleDetail.setVehicleNo(vehicleNo);
        vehicleDetailRepository.save(vehicleDetail);
        return ResponseEntity.noContent().build();
    }

    public VehicleDetail createOrUpdateVehicleDetailService(VehicleDetail vehicleDetail,
                                                      long vehicleNo,
                                                      long userId,
                                                      MultipartFile file
                                                      ) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());

        VehicleDetail vehicle;
        Optional<VehicleDetail> result = this.vehicleDetailRepository.findById(vehicleNo);
        if (result.isEmpty()) {
            throw new Exception("Invalid vehicle number passed.");
        } else {
            vehicle = result.get();
            vehicle.setVehicleType(vehicleDetail.getVehicleType());
            vehicle.setMake(vehicleDetail.getMake());
            vehicle.setModel(vehicleDetail.getModel());
            vehicle.setVarient(vehicleDetail.getVarient());
            vehicle.setSeries(vehicleDetail.getSeries());
        }

        VehicleDetail finalResult =  vehicleDetailRepository.save(vehicle);
        if(finalResult == null)
            throw new Exception("Fail to insert or update vehicle data.");


        return finalResult;
    }

    public List<VehicleDetail> getAllVehicleDetailService() {
        var result = this.vehicleDetailRepository.findAll();
        return result;
    }

    public Optional<VehicleDetail> getVehicleDetailByVehicleNoService(long vehicleNo) {
        var result = this.vehicleDetailRepository.findById(vehicleNo);
        return result;

    }

    public String deleteVehicleDetailByVehicleNoService(long vehicleNo) {
       this.vehicleDetailRepository.deleteById(vehicleNo);
       return "VehicleDetail has been deleted";
    }
}
