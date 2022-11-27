package com.example.restservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.restservice.Model.AudioFile;

public interface AudioFileRepository extends MongoRepository<AudioFile, String> {
    @Query("{name:'?0'}")
    AudioFile findItemByName(String name);

    public long count();
}
