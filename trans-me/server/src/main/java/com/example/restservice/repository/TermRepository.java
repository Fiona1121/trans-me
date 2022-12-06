package com.example.restservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.restservice.Model.Term;

// import java.util.List;

public interface TermRepository extends MongoRepository<Term, String>{
    @Query("{name:'?0'}")
    Term findItemByName(String name);

    public long count();
}
