package com.example.restservice.Drive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.restservice.Drive.GoogleDriveUtils;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class GetSubFolders {
    // Returns the subfolders of the transme folder
    // com.google.api.services.drive.model.File
    public static final List<File> getGoogleSubFolders() throws IOException {
        String folderIdParent = "130DakidoW74dLcOt0sj_3-hppX_ia25E";

        Drive driveService = GoogleDriveUtils.getDriveService();

        String pageToken = null;
        List<File> list = new ArrayList<File>();

        String query = " mimeType = 'application/vnd.google-apps.folder' " //
                + " and '" + folderIdParent + "' in parents";

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime
                    .setFields("nextPageToken, files(id, name, createdTime)")//
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
