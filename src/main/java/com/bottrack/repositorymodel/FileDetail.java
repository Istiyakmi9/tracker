package com.bottrack.repositorymodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "filedetail")
public class FileDetail {
    @Id
    @Column(name = "FileDetailId")
    private int fileDetailId;
    @Column(name = "FileName")
    private String fileName;
    @Column(name = "Extension")
    private String extension;
    @Column(name = "FileSize")
    private double fileSize;
    @Column(name = "FilePath")
    private String filePath;
    @Column(name = "UserId")
    public long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getFileDetailId() {
        return fileDetailId;
    }

    public void setFileDetailId(int fileDetailId) {
        this.fileDetailId = fileDetailId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
