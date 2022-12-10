package com.example.restservice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.restservice.Model.Account;
import com.example.restservice.Model.AudioFile;
import com.example.restservice.Repository.AccountRepository;
import com.example.restservice.Repository.AudioFileRepository;
import com.example.restservice.Response.Msg;
import com.example.restservice.Service.Payload.Payload;

public class AudioFilesService {
    @Autowired
    AudioFileRepository audioFileRepository;

    @Autowired
    AccountRepository accountRepository;

    public Payload<Msg, List<AudioFile>> getAudioFiles(String username) {
        System.out.println("Getting audio files from a username");

        List<AudioFile> audioFiles = new ArrayList<AudioFile>();
        
        Account account = accountRepository.findByUsername(username);

        if (account != null) {
            List<String> audioFilesId = account.getAudioFilesId();
            if (audioFilesId.isEmpty()) {
                System.out.println("No audio files found");
                return new Payload <Msg, List<AudioFile>> (
                        new Msg(
                            "warning",
                            "No audio files found"
                        ),
                        audioFiles
                    );
            }
            else {
                audioFilesId.forEach((id) -> {
                    Optional<AudioFile> audioFile = audioFileRepository.findById(id);
                    if (audioFile.isPresent()) {
                        audioFiles.add(audioFile.get());
                    }
                });

                if (audioFiles.isEmpty()) {
                    System.out.println("No audio files found");
                    return new Payload <Msg, List<AudioFile>> (
                            new Msg(
                                "warning",
                                "No audio files found"
                            ),
                            audioFiles
                        );
                }
                else if (audioFiles.size() != audioFilesId.size()) {
                    System.out.println("Some of the Audio Files were not found");
                    System.out.println("Audio File data :　" + audioFiles);
                    return new Payload <Msg, List<AudioFile>> (
                            new Msg(
                                "warning",
                                "Some of the Audio Files were not found"
                            ),
                            audioFiles
                        );
                }
                else {
                    System.out.println("Success, return all Audio Files data");
                    System.out.println("Audio Files data :　" + audioFiles);
                    return new Payload <Msg, List<AudioFile>> (
                            new Msg(
                                "success",
                                "return all Audio Files data"
                                ),
                            audioFiles
                        );
                }
            }
        }
        else {
            System.out.println("Username not found");
            return new Payload <Msg, List<AudioFile>> (
                    new Msg(
                        "error",
                        "Username not found"
                    ),
                    audioFiles
                );
        }
    }

    public Payload<Msg, AudioFile> postAudioFile(String username, String name, String format) {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            System.out.println("Account not found");
            return new Payload<Msg,AudioFile>(
                new Msg(
                    "error", 
                    "Account not found"
                    ),
                new AudioFile()
            );
        }

        System.out.println("Create audio file");

        AudioFile newAudioFile = new AudioFile("", name, "", "", format);

        AudioFile savedAudioFile = audioFileRepository.save(newAudioFile);

        if (savedAudioFile != null) {
            System.out.println("Success, return audio file data");
            System.out.println("Audio file data : " + savedAudioFile);

            // Update the list of audio files of the given user
            List<String> audioFilesId = account.getAudioFilesId();
            audioFilesId.add(savedAudioFile.getId());
            account.setAudioFilesId(audioFilesId);
            accountRepository.save(account);

            return new Payload <Msg, AudioFile> (
                    new Msg(
                        "success",
                        "return audio file data"
                        ),
                    savedAudioFile
                );
        }
        else {
            System.out.println("Error, couldn't add audio file to mongodb");
            return new Payload <Msg, AudioFile> (
                    new Msg(
                        "error",
                        "could not add audio file to db"
                        ),
                    savedAudioFile
                );
        }
    }
}
