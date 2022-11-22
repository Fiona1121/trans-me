package com.example.restservice;

import com.example.restservice.model.Term;
import com.example.restservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class MongoDbSpringBootApplication implements CommandLineRunner {
    @Autowired
    ItemRepository termRepo;
    public static void main(String[] args) {
        SpringApplication.run(MongoDbSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--CREATE Term--");
        createItem("name", "link", "description");
        List<Term> itemList = returnAllItems();
        itemList.forEach(System.out::println);
        System.out.println(getCountOfTerms());
    }

    public void createItem(String name, String link, String description) {
        termRepo.save(new Term(name, link, description));
    }

    public List<Term> returnAllItems() {
        return termRepo.findAll();
    }

    public long getCountOfTerms() {
        return termRepo.count();
    }
}
