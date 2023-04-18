package com.bottrack.filehandler;

import com.bottrack.model.FileStorageProperties;
import com.bottrack.repositorymodel.FileDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;

@Component
public class FileManager {
    private final Logger logger = LoggerFactory.getLogger(FileManager.class);
    private final String basePath;

    @Autowired
    public FileManager(FileStorageProperties fileStorageProperties) throws IOException {
        logger.info("Getting static folder class path");
        // basePath = getClass().getClassLoader().getResource("static").getPath();
        basePath = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize().toString();
        logger.info("Static folder class path: " + basePath);
    }

    public FileDetail uploadFile(MultipartFile file, long userId, String fileName, String existingFilePath) throws Exception {
        FileDetail fileDetail = null;
        String name = file.getOriginalFilename();

        if (file != null && name != null && !name.isEmpty()) {
            fileDetail = new FileDetail();
            String ext = name.substring(name.lastIndexOf(".") + 1);
            String nameOnly = name.substring(0, name.lastIndexOf("."));
            String relativePath = Paths.get("user_" + String.valueOf(userId)).toString();

            if(name.contains(".."))
                throw new Exception("File name contain invalid character.");

            Path targetDirectory = Paths.get(basePath, relativePath)
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

            if (!existingFilePath.isEmpty()) {
                var existingFile = targetDirectory.resolve(existingFilePath);
                if(Files.exists(existingFile))
                    Files.delete(existingFile);
            }

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            fileDetail.setFilePath(relativePath);
            fileDetail.setExtension(ext);
        }

        return fileDetail;
    }
}
