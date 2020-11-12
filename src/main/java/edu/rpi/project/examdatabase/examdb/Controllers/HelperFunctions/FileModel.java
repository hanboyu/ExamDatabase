package edu.rpi.project.examdatabase.examdb.Controllers.HelperFunctions;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
