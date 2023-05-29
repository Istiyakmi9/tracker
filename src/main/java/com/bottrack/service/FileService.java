package com.bottrack.service;

import com.bottrack.repositorymodel.FileDetail;
import com.bottrack.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
public class FileService {
    FileRepository fileRepository;
    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    public FileDetail getFileByName(FileDetail fileDetail) {
        var existingFileDetaile = this.fileRepository.filterByName(fileDetail.getUserId(), fileDetail.getFileName());
        return existingFileDetaile;
    }

    public FileDetail updateFileDetailByName(FileDetail fileDetail) {
        var record = this.fileRepository.filterByName(fileDetail.getUserId(), fileDetail.getFileName());
        if (record == null){
            long len = this.fileRepository.count();
            record = fileDetail;
            record.setFileDetailId((int)len + 1);
        } else {
            record.setFilePath(fileDetail.getFilePath());
            record.setFileSize(fileDetail.getFileSize());
            record.setExtension(fileDetail.getExtension());
        }

        var result = this.fileRepository.save(record);
        return result;
    }

    public FileDetail addOrUpdateFileDetail(FileDetail fileDetail) {
        FileDetail finalRecord;
        var record = this.fileRepository.findById(fileDetail.getFileDetailId());
        if (record.isEmpty()){
            long len = this.fileRepository.count();
            finalRecord = fileDetail;
            finalRecord.setFileDetailId((int)len + 1);
        } else {
            finalRecord = record.get();
            finalRecord.setFilePath(fileDetail.getFilePath());
            finalRecord.setFileSize(fileDetail.getFileSize());
            finalRecord.setExtension(fileDetail.getExtension());
        }

        return this.fileRepository.save(finalRecord);
    }

    public FileDetail getVehicleFileDetail(long userId) {
        return this.fileRepository.filterByName(userId, "vehicle%");
    }

    public FileDetail getRatingFileDetail(long userId) {
        return this.fileRepository.filterByName(userId, "rating%");
    }

    public FileDetail getVehicleFileDetailById(int fileDetailId) {
        var fileDetail = this.fileRepository.findById(fileDetailId);
        return fileDetail.orElse(null);
    }

    public void deleteVehicleFileDetail(long userId) {
        var fileDetail = getVehicleFileDetail(userId);
        if(fileDetail != null) {
            this.fileRepository.delete(fileDetail);
        }
    }

    public FileDetail getUserFileDetail(long userId) {
        return this.fileRepository.filterByName(userId, "user%");
    }

    public void deleteUserFileDetail(long userId) {
        var fileDetail = getUserFileDetail(userId);
        if(fileDetail != null) {
            this.fileRepository.delete(fileDetail);
        }
    }
}
