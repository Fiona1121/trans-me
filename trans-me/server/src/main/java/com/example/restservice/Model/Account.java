package com.example.restservice.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Account {
    @Id private Id id;
    private String username;
    private String password;
    private Date expiration_date;
    private List<String> audio_files; 
        // should be audio files data type
    private List<String> blocks;
        // should be block data type
        
    public Account(Id id, String username, String password, Date expiration_date, List<String> audio_files,
            List<String> blocks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.expiration_date = expiration_date;
        this.audio_files = audio_files;
        this.blocks = blocks;
    }
    
    public Id getId() {
        return id;
    }
    public void setId(Id id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getExpiration_date() {
        return expiration_date;
    }
    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }
    public List<String> getAudio_files() {
        return audio_files;
    }
    public void setAudio_files(List<String> audio_files) {
        this.audio_files = audio_files;
    }
    public List<String> getBlocks() {
        return blocks;
    }
    public void setBlocks(List<String> blocks) {
        this.blocks = blocks;
    }
}