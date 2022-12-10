package com.example.restservice.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.restservice.Drive.*;

@Tag(name = "index")
@RestController
public class index {
    
    @GetMapping("/")
    @Operation(summary = "get index", description = "get it！")
    public String getIndex() {
        
        // return new Account(null, param, param, null, null, null);
        // return "Hello " + param;
        return "Hello ";
    }

    @Autowired
    CreateFolder createFolder;

    @PostMapping("/testInit")
    public String testInit(){
        String test =  "C:\\Users\\user\\Desktop\\test.wav";
        
        // File folder
        String fileId;
        try {
            fileId = createFolder.createGoogleFolder("transcription");
            System.out.println("Created folder with id= "+ fileId);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("wrong");
            e.printStackTrace();
        }

        File file = new File(test);
        // MultipartFile mpFile = new MockMultipartFile("newfile.mp3", new FileInputStream(file));

        // File googleFile = uploadFile(mpFile, "1syckAI2pLO-WZT2BR7J-B01i0jhDo8D4");

        // System.out.println("Created audio file with id: " + googleFile.getId());
        // System.out.println("WebContentLink: " + googleFile.getWebContentLink() );

        return "done";
    }

}

