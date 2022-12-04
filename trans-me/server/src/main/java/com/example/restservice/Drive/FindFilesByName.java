package com.example.restservice.Drive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.restservice.Drive.GoogleDriveUtils;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class FindFilesByName {
    public static final List<File> getGoogleFilesByName(String folderIdParent, String fileNameLike) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        String pageToken = null;
        List<File> list = new ArrayList<File>();

        String query = " name contains '" + fileNameLike + "' " //
                + " and mimeType != 'application/vnd.google-apps.folder' "
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

    // public static void main(String[] args) throws IOException {
    //     String folderIdParent = "130DakidoW74dLcOt0sj_3-hppX_ia25E";
    //     List<File> rootGoogleFolders = getGoogleFilesByName(folderIdParent, "newfile.txt");
    //     for (File folder : rootGoogleFolders) {

    //         System.out.println("Mime Type: " + folder.getMimeType() + " --- Name: " + folder.getName());
    //     }

    //     System.out.println("Done!");
    // }
}
