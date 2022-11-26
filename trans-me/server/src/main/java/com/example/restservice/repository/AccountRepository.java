package com.example.restservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.restservice.Model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{userID:'?0', password:'?1'}")
    Account findItemByName(String userID, String password);

    public long count();
}
