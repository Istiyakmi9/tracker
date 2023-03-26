package com.bottrack.service;

import com.bottrack.repositorymodel.FileDetail;
import com.bottrack.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
