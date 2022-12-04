package com.example.restservice.Drive;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.restservice.Drive.GoogleDriveUtils;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class CreateFolder {
    // Call this function with folderId = 130DakidoW74dLcOt0sj_3-hppX_ia25E
    // To create folder inside transme folder in google drive
    public static final File createGoogleFolder(String folderName) throws IOException {
        String folderIdParent = "130DakidoW74dLcOt0sj_3-hppX_ia25E";
        
        File fileMetadata = new File();

        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        if (folderIdParent != null) {
            List<String> parents = Arrays.asList(folderIdParent);

            fileMetadata.setParents(parents);
        }
        Drive driveService = GoogleDriveUtils.getDriveService();

        // Create a Folder.
        // Returns File object with id & name fields will be assigned values
        File file = driveService.files().create(fileMetadata).setFields("id, name").execute();

        return file;
    }

    // public static void main(String[] args) throws IOException {

    //     // Create a Folder inside transme Folder
    //     File folder = createGoogleFolder("130DakidoW74dLcOt0sj_3-hppX_ia25E", "test");
        
    //     System.out.println("Created folder with id= "+ folder.getId());
    //     System.out.println("                    name= "+ folder.getName());

    //     System.out.println("Done!");
    // }
}
