package com.example.restservice.repository;

import com.example.restservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{name:'?0'}")
    Account findItemByName(String name);

    public long count();
}
