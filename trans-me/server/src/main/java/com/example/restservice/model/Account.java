package com.example.restservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.restservice.Model.AudioFile;
import com.example.restservice.Model.Block;

import java.util.Date;
import java.util.List;

@Document("accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    private String userID; // id
    // 指定成 Id mongo 就會把它變成 identifier ?!

    private String username;
    private String password;
    private Date expirationTime;
    private List<AudioFile> audioFilesID;
    private List<Block> blocksID;

    // List<String>
}
