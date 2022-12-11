package com.example.restservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.Response.Msg;
import com.example.restservice.Service.Payload.Payload;
import com.example.restservice.Drive.DeleteFile;
import com.example.restservice.Drive.GetSubFolders;
import com.example.restservice.Model.Account;
import com.example.restservice.Repository.AccountRepository;
import com.example.restservice.Repository.AudioFileRepository;
import com.example.restservice.Repository.BlockRepository;
import com.example.restservice.Repository.TermRepository;

@Service
public class SystemService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired 
    AudioFileRepository AudioFileRepository;

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    TermRepository termRepository;

    public Payload <Msg, String> reset() {
        System.out.println("System reset：");

        resetDB();
        resetDrive();
        
        System.out.println(" completed");
        return new Payload <Msg, String> (
            new Msg(
                "success",
                "data is reset"
            ),
            ""
        );
    }

    public void resetDB() {
        System.out.println("Reset DB");

        accountRepository.deleteAll();
        AudioFileRepository.deleteAll();
        blockRepository.deleteAll();
        termRepository.deleteAll();
    }

    public void resetDrive() {
        try {
            List<String> listOfIds = GetSubFolders.getGoogleSubFolders();
            listOfIds.forEach((id) -> {
                try {
                    DeleteFile.deleteFile(id);
                }
                catch (Exception f) {
                    System.out.println(f);
                }
            });
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

