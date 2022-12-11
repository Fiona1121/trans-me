package com.example.restservice.Drive;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.google.api.services.drive.Drive;

public class DownloadFile {
    public static ByteArrayOutputStream downloadFile(String fileId) throws Exception {
        Drive driveService = GoogleDriveUtils.getDriveService();
        OutputStream outputStream = new ByteArrayOutputStream();

        driveService.files().get(fileId).executeMediaAndDownloadTo(outputStream);

        return (ByteArrayOutputStream) outputStream;
    }
}
