package com.bottrack.filehandler;

import com.bottrack.repositorymodel.FileDetail;
import com.bottrack.model.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Component
public class FileManager {
    private FileStorageProperties fileStorageProperties;

    @Autowired
    public FileManager(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    public FileDetail uploadFile(MultipartFile file, long userId, String fileName) throws Exception {
        FileDetail fileDetail = new FileDetail();
        String name = file.getOriginalFilename();

        String ext = name.substring(name.lastIndexOf(".") + 1);
        String nameOnly = name.substring(0, name.lastIndexOf("."));
        String relativePath = Paths.get(fileStorageProperties.getUploadDir(), "user_" + String.valueOf(userId)).toString();

        if(name.contains(".."))
            throw new Exception("File name contain invalid character.");

        Path targetDirectory = Paths.get(fileStorageProperties.getUploadDir(), "user_" + String.valueOf(userId))
                .toAbsolutePath()
                .normalize();
        if(Files.notExists(targetDirectory))
            Files.createDirectories(targetDirectory);

        String newFileName = null;
        if (fileName.isEmpty()) {
            fileDetail.setFileName(nameOnly);
            newFileName = nameOnly;
        } else {
            fileDetail.setFileName(fileName);
            newFileName = fileName + "." + ext;
        }
        Path targetPath = targetDirectory.resolve(newFileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        fileDetail.setFilePath(relativePath);
        fileDetail.setExtension(ext);
        return fileDetail;
    }
}