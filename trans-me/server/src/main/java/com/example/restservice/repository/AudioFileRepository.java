package com.example.restservice.repository;

import com.example.restservice.model.AudioFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AudioFileRepository extends MongoRepository<AudioFile, String> {
    @Query("{name:'?0'}")
    AudioFile findItemByName(String name);

    public long count();
}
