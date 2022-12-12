package com.example.restservice.Service;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
// Imports the Google Cloud client library
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.protobuf.ByteString;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.Duration;

import com.example.restservice.Model.AudioFile;
import com.example.restservice.Repository.AudioFileRepository;
import com.example.restservice.Response.Msg;
import com.example.restservice.Service.Payload.Payload;
import com.example.restservice.Transcription.TranscriptionSupport;

@Service
public class Transcription {

    @Autowired
    AudioFileRepository audioFileRepository;
    
    public Payload<Msg, String> transcribe(String username, String audioFileId) {
        
        System.out.println("Transcription：");
        System.out.println("file : " + username + "-" + audioFileId);

        String warningMessage = "";
        
        Optional<AudioFile> fetchedFile = audioFileRepository.findById(audioFileId);
        if (!fetchedFile.isPresent()) {
            System.out.println("fetchedFile : " + fetchedFile);
            System.out.println("audio file not found");
            return new Payload <Msg, String> (
                new Msg(
                    "error",
                    "audio file not found"
                ),
                ""
            );
        }
        // List<AudioFile> fetchedFile = audioFileRepository.findByDriveId(audioFileId);
        // if (fetchedFile.size() == 0) {
        //     System.out.println("fetchedFile : " + fetchedFile);
        //     System.out.println("audio file not found (list.size() == 0)");
        //     return new Payload <Msg, String> (
        //         new Msg(
        //             "error",
        //             "audio file not found"
        //         ),
        //         ""
        //     );
        // }

        
        // check mongo，return error if not found or wrong data
        String language = fetchedFile.get().getLanguage();
        // String language = fetchedFile.get(0).getLanguage();
        if ( (language == null) || 
        !((language.equals("zh-TW")) || (language.equals("en-US")))) {
            language = "zh-TW";
            System.out.println("unsupported language, use default (zh-TW)");
            warningMessage += "Unsupported language, use default (zh-TW).";
        }
        int sampleRate = fetchedFile.get().getSampleRate();
        // int sampleRate = fetchedFile.get(0).getSampleRate();
        if ( (sampleRate == 0) ) { 
            // add range limitation?
            sampleRate = 44100;
            System.out.println("sample rate not specified, use default (0 Hz)");
            warningMessage += " Sample rate not specified, use default (0 Hz).";
        }

        TranscriptionSupport support = new TranscriptionSupport(
            audioFileId,
            username + "-" + audioFileId + ".wav");
        String filePath = support.downloadFile();

        if (filePath == null) {
            System.out.println("Something is wrong while downloading the audio file");
            return new Payload <Msg, String>(
                new Msg(
                    "error", 
                    "Something is wrong while downloading the audio file. Please try again."),
                ""
            );
        }
        else {
            System.out.println("try transcription");
            String transcriptionResult;
            Payload <Msg, String> result = new Payload<Msg,String>(null, "");
            
            try {
                transcriptionResult = Transcription.asyncRecognizeFile(
                    filePath, language, sampleRate
                );
            } catch (Exception e) {
                e.printStackTrace();
                transcriptionResult = null;
            }
            
            if (transcriptionResult != null) {
                System.out.println("Transcription completed");
                result.setMsg( new Msg(
                    "success", 
                    "Transcription completed")
                );
                result.setData(transcriptionResult);
            }
            else {
                System.out.println("Transcription failed");
                result.setMsg( new Msg(
                    "error", 
                    "Transcription failed")
                );
            }
            
            support.deleteFile();
            result.getMsg().setMsg(result.getMsg().getMsg() + warningMessage);;
            return result;
        }

    }
    
    
    /**
     * Performs non-blocking speech recognition on remote FLAC file and prints the transcription.
     *
     * @param gcsUri the path to the remote LINEAR16 audio file to transcribe.
     */
    public static String asyncRecognizeGcs(String gcsUri) throws Exception {
        // Configure polling algorithm
        SpeechSettings.Builder speechSettings = SpeechSettings.newBuilder();
        TimedRetryAlgorithm timedRetryAlgorithm =
            OperationTimedPollAlgorithm.create(
                RetrySettings.newBuilder()
                    .setInitialRetryDelay(Duration.ofMillis(500L))
                    .setRetryDelayMultiplier(1.5)
                    .setMaxRetryDelay(Duration.ofMillis(5000L))
                    .setInitialRpcTimeout(Duration.ZERO) // ignored
                    .setRpcTimeoutMultiplier(1.0) // ignored
                    .setMaxRpcTimeout(Duration.ZERO) // ignored
                    .setTotalTimeout(Duration.ofHours(24L)) // set polling timeout to 24 hours
                    .build());
        speechSettings.longRunningRecognizeOperationSettings().setPollingAlgorithm(timedRetryAlgorithm);
    
        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create(speechSettings.build())) {
    
        // Configure remote file request for FLAC
        RecognitionConfig config =
            RecognitionConfig.newBuilder()
                .setEncoding(AudioEncoding.LINEAR16) //(AudioEncoding.FLAC)
                .setLanguageCode("zh-TW") // en-US
                .setSampleRateHertz(44100) // (16000)
                .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();
    
        // Use non-blocking call for getting file transcription
        OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
            speech.longRunningRecognizeAsync(config, audio);
        while (!response.isDone()) {
            System.out.println("Waiting for response...");
            Thread.sleep(10000);
        }
    
        List<SpeechRecognitionResult> results = response.get().getResultsList();
    
        for (SpeechRecognitionResult result : results) {
            // There can be several alternative transcripts for a given chunk of speech. Just use the
            // first (most likely) one here.
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
            System.out.printf("Transcription: %s\n", alternative.getTranscript());
        }
        return results.get(0).getAlternatives(0).getTranscript();

        }
    }    

    /**
 * Performs non-blocking speech recognition on raw PCM audio and prints the transcription. Note
 * that transcription is limited to 60 seconds audio.
 *
 * @param fileName the path to a PCM audio file to transcribe.
 */
    public static String asyncRecognizeFile(String fileName, String language, int sampleRate) throws Exception {
        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create()) {
    
        System.out.println("speech to text : local file");
        System.out.println("fileName" +fileName);

        Path path = Paths.get(fileName);
        byte[] data = Files.readAllBytes(path);
        ByteString audioBytes = ByteString.copyFrom(data);
    
        // Configure request with local raw PCM audio
        RecognitionConfig config =
            RecognitionConfig.newBuilder()
                .setEncoding(AudioEncoding.LINEAR16)
                .setLanguageCode(language) // "zh-TW", en-US
                .setSampleRateHertz(sampleRate) // 44100
                .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
    
        // Use non-blocking call for getting file transcription
        OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
            speech.longRunningRecognizeAsync(config, audio);
    
        while (!response.isDone()) {
            System.out.println("Waiting for response...");
            // System.out.println("response : " + response.toString());
            Thread.sleep(10000);
        }
    
        // System.out.println("response : " + response.toString());
        List<SpeechRecognitionResult> results = response.get().getResultsList();
        // System.out.println("results : " + results);
    
        for (SpeechRecognitionResult result : results) {
            // There can be several alternative transcripts for a given chunk of speech. Just use the
            // first (most likely) one here.
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
            System.out.printf("Transcription: %s%n", alternative.getTranscript());
        }
        return results.get(0).getAlternatives(0).getTranscript();
    }
    }
}
