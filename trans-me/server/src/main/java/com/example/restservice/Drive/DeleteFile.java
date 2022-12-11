package com.example.restservice.Drive;

import com.google.api.services.drive.Drive;

public class DeleteFile {
    public static void deleteFile(String fileId) throws Exception {
        Drive driveService = GoogleDriveUtils.getDriveService();

        driveService.files().delete(fileId).execute();

        System.out.println("The file " + fileId + " was deleted succesfully!");
    }
}
