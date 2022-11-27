package com.example.restservice.model;

import com.example.restservice.model.AudioFile;
import com.example.restservice.model.Block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    private String userID;

    private String password;
    private Date expirationTime;
    private List<AudioFile> audio_files;
    private List<Block> blocks;
}
