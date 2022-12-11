package com.example.restservice.Drive; 

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
// import org.springframework.mock.web.MockMultipartFile;

import com.google.api.services.drive.model.File;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;

@Service
public class CreateAudioFile {
    // Input: 
    // file: the file as a multipart file coming from a POST method
    // folderId: Account's folder ID to upload the file to
    // Returns:
    // List of two elements
    // First element is the file id from google drive
    // Second element is the download URL for the frontend to download the file
    public static List<String> uploadFile(MultipartFile file, String folderId) {
        try {
            if (folderId == "" || folderId == null) {
                folderId = "130DakidoW74dLcOt0sj_3-hppX_ia25E";
            }
            File fileMetadata = new File();

            fileMetadata.setName(file.getName());
            List<String> parents = Arrays.asList(folderId);
            fileMetadata.setParents(parents);
            
            Drive driveService = GoogleDriveUtils.getDriveService();
            File fileResponse = driveService.files().create(fileMetadata, new InputStreamContent(
                    file.getContentType(),
                    new ByteArrayInputStream(file.getBytes())
                ))
                .setFields("id, webContentLink").execute();
            List<String> responseList = Arrays.asList(fileResponse.getId(), fileResponse.getWebContentLink());
            return responseList;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
