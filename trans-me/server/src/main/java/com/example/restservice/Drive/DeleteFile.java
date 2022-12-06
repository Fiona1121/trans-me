package com.example.restservice.Drive;

import com.example.restservice.Drive.GoogleDriveUtils;
import com.google.api.services.drive.Drive;

public class DeleteFile {
    public static void deleteFile(String fileId) throws Exception {
        Drive driveService = GoogleDriveUtils.getDriveService();

        driveService.files().delete(fileId).execute();

        System.out.println("The file " + fileId + " was deleted succesfully!");
    }

    // public static void main(String[] args) throws Exception {

    //     String fileId = "1cdtxbGStvC441ZrKYGM2ehW6kvm6Bmbi";

    //     deleteFile(fileId);

    //     System.out.println("Deleted Google File with id = " + fileId);

    //     System.out.println("Done!");
    // }
}
