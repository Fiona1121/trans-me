package com.example.restservice;

import com.example.restservice.Model.Term;
import com.example.restservice.Repository.TermRepository;

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
    TermRepository termRepo;
    public static void main(String[] args) {
        SpringApplication.run(MongoDbSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // System.out.println("--------START--------");
        // createItem("otherTest", "www.meow2.com", "meow2");

        // System.out.println("ALL TERMS: ");
        // List<Term> itemList = returnAllItems();
        // for (Term t: itemList) {
        //     System.out.println(t.getName() + ", " + t.getLink() + ", " + t.getDescription());
        // }

        // String customName = "name";
        // System.out.println("Find term by name: " + customName);
        // Term termQueryByName = findTermByName(customName);
        // System.out.println("Term description: " + termQueryByName.getDescription());

        // updateTermDescription(customName, "newDescription");

        // System.out.println("Number of terms in collection: " + getCountOfTerms());
    }

    public void createItem(String name, String link, String description) {
        termRepo.save(new Term(name, link, description));
    }

    public List<Term> returnAllItems() {
        return termRepo.findAll();
    }

    public Term findTermByName(String name) {
        return termRepo.findItemByName(name);
    }

    public long getCountOfTerms() {
        return termRepo.count();
    }

    public void updateTermDescription(String name, String description) {
        Term term = termRepo.findItemByName(name);
        term.setDescription(description);
        Term updatedTerm = termRepo.save(term);
        if (updatedTerm != null) System.out.println("Successfully updated");
    }

    public void deleteTermByName(String name) {
        termRepo.deleteById(name);
    }
}
