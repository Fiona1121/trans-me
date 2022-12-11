package com.example.restservice.Drive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.restservice.Drive.GoogleDriveUtils;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class GetAllFilesInSubFolder {
    // Pass down the folder id of the parent to get all the files inside
    public static final List<File> getGoogleFilesInFolder(String folderIdParent) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        String pageToken = null;
        List<File> list = new ArrayList<File>();

        String query = "mimeType != 'application/vnd.google-apps.folder' "
                + " and '" + folderIdParent + "' in parents";

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime, mimeType
                    .setFields("nextPageToken, files(id, name, createdTime, mimeType)")//
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return list;
    }
}
