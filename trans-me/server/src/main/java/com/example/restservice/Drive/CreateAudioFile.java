package com.example.restservice.Drive; 

import java.io.IOException;
import java.util.Collections;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.lang.Object;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import com.google.api.services.drive.model.File;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.util.IOUtils;
import com.google.api.services.drive.Drive;

public class CreateAudioFile {
    public static File uploadFile(MultipartFile file, String folderId) {
        try {
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
            return fileResponse;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        java.io.File file = new java.io.File("./src/main/java/com/example/restservice/Drive/everybodys_circulation.mp3");
        MultipartFile mpFile = new MockMultipartFile("newfile.mp3", new FileInputStream(file));

        File googleFile = uploadFile(mpFile, "1syckAI2pLO-WZT2BR7J-B01i0jhDo8D4");

        System.out.println("Created audio file with id: " + googleFile.getId());
        System.out.println("WebContentLink: " + googleFile.getWebContentLink() );

        System.out.println("Done!");
    }
}
