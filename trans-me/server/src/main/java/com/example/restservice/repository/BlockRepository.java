package com.example.restservice.repository;

import com.example.restservice.model.Block;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BlockRepository extends MongoRepository<Block, String> {
    @Query("{name:'?0'}")
    Block findItemByName(String name);

    public long count();
}
