package com.example.restservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.restservice.Model.Block;

public interface BlockRepository extends MongoRepository<Block, String> {
    @Query("{id:'?0'}")
    Block findItemByName(String id);

    public long count();
}
