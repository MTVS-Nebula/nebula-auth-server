package com.nebula.nebula_auth.app.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ATTACH", schema = "file")
@SequenceGenerator(
        name = "SEQ_ATTACH_ID_GENERATOR",
        sequenceName = "SEQ_ATTACH_ID",
        initialValue = 1,
        allocationSize = 1
)
public class Attach {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ATTACH_ID_GENERATOR"
    )
    @Column(name="ATTACH_ID")
    private int id;
    @Column(name="ORIGINAL_NAME", nullable = false)
    private String originalName;
    @Column(name = "SAVED_NAME", nullable = false)
    private String savedName;
    @Column(name = "SAVED_PATH", nullable = false, unique = true)
    private String savedPath;
    @Column(name = "FILE_TYPE")
    private String fileType;
    @Column(name = "UPLOAD_DATE")
    private String uploadDate;
    @Column(name = "IS_USED", nullable = false)
    private String isUsed;

    public Attach() {
    }

    public Attach(int id, String originalName, String savedName, String savedPath, String fileType, String uploadDate, String isUsed) {
        this.id = id;
        this.originalName = originalName;
        this.savedName = savedName;
        this.savedPath = savedPath;
        this.fileType = fileType;
        this.uploadDate = uploadDate;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "Attach{" +
                "id=" + id +
                ", originalName='" + originalName + '\'' +
                ", savedName='" + savedName + '\'' +
                ", savedPath='" + savedPath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", isUsed='" + isUsed + '\'' +
                '}';
    }
}
