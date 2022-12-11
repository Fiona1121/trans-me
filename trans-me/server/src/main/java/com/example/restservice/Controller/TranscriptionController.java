package com.example.restservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.Request.transcription.PostTranscriptionRequest;
import com.example.restservice.Response.CommonResponse;
import com.example.restservice.Response.Msg;
import com.example.restservice.Service.Transcription;

// @Tag(name = "Block Controller")
@RestController
@RequestMapping("/transcription")
public class TranscriptionController {
    
    @Autowired
    Transcription transcription;
    
    // postTranscription
    @PostMapping("")
    public CommonResponse postTranscription(@RequestBody PostTranscriptionRequest req) {
    
    // "C:\\Users\\user\\Desktop\\test.wav"

        try {
            System.out.println("Transcription succeded");
            return new CommonResponse <String>(
                new Msg("success", "Transcription succeded"),
                Transcription.asyncRecognizeFile(req.getData().getAudioFileId(), "zh-TW", 44100)
            );
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Transcription failed");
            e.printStackTrace();
            return new CommonResponse <String>(
                new Msg("error", "Transcription failed"),
                ""
            );
        }
        
    }
}
