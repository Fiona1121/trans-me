package com.example.restservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("audiofiles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AudioFile {
    @Id
    private String id;

    private String name;
    private String url;
    private String driveId;
    private String format;
}
