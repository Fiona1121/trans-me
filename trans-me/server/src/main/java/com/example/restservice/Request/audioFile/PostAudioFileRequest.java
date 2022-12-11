package com.example.restservice.Request.audioFile;

import org.springframework.web.multipart.MultipartFile;

import com.example.restservice.Model.AudioFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostAudioFileRequest {
    @Getter
    @Setter
    public class RequestData {
        private String username;
        private String name;
        private String format;
        private MultipartFile file;
    }

    private RequestData data;
}
