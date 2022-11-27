package com.example.restservice;

import com.example.restservice.Model.Account;
import com.example.restservice.Model.Term;
import com.example.restservice.Repository.AccountRepository;
import com.example.restservice.Repository.TermRepository;
import com.example.restservice.Service.AccountService;
import com.example.restservice.Service.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class MongoDbSpringBootApplication implements CommandLineRunner {
    @Autowired
    // TermRepository termRepo;
    AccountRepository accountRepo;

    @Autowired
    AccountService accountService;
    
    public static void main(String[] args) {
        SpringApplication.run(MongoDbSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // testing
        System.out.println("--------START--------");
        
        // createItem("000", "test", "meow2");
        // delete("test");

        // Account test = accountRepo.findByUsername("POST");
        // test.setPassword("new password");
        // accountRepo.save(test);

        // System.out.println(accountRepo.findByUsername("POST"));
        //     // 有找到
        // System.out.println(accountRepo.findByUsername("GG"));
        //     // null

        // ArrayList<String> test = new ArrayList<String>();
        // test.add("testID");

        // System.out.println(accountService.update(new Account(
        //     "",
        //     "POST",
        //     "testPW",
        //     test,
        //     new ArrayList<String>()
        // )));

        // System.out.println(login.login("POST", ""));
        // System.out.println(login.login("POST", "no"));
        // System.out.println(login.login("test", ""));

        System.out.println("ALL ACCOUNTS: ");
        List<Account> itemList = returnAllItems();
        for (Account t: itemList) {
            System.out.println(t.toString());
        }

        // String customName = "name";
        // System.out.println("Find term by name: " + customName);
        // Term termQueryByName = findTermByName(customName);
        // System.out.println("Term description: " + termQueryByName.getDescription());

        // updateTermDescription(customName, "newDescription");

        // System.out.println("Number of terms in collection: " + getCountOfTerms());
    }

    public void createItem(String id, String username, String password) {
        accountRepo.save(
            // new Account(id, username, password, 
            //     new ArrayList<String>(), new ArrayList<String>())
            new Account(username = username, password = password)
        );
    }

    public List<Account> returnAllItems() {
        return accountRepo.findAll();
    }

    // public Term findTermByName(String name) {
    //     return termRepo.findItemByName(name);
    // }

    // public long getCountOfTerms() {
    //     return termRepo.count();
    // }

    // public void updateTermDescription(String name, String description) {
    //     Term term = termRepo.findItemByName(name);
    //     term.setDescription(description);
    //     Term updatedTerm = termRepo.save(term);
    //     if (updatedTerm != null) System.out.println("Successfully updated");
    // }

    public void delete(String username) {
        accountRepo.deleteAllByUsername(username);
    }

    // =============================================================

    // public void createItem(String name, String link, String description) {
    //     termRepo.save(new Term(name, link, description));
    // }

    // public List<Term> returnAllItems() {
    //     return termRepo.findAll();
    // }

    // public Term findTermByName(String name) {
    //     return termRepo.findItemByName(name);
    // }

    // public long getCountOfTerms() {
    //     return termRepo.count();
    // }

    // public void updateTermDescription(String name, String description) {
    //     Term term = termRepo.findItemByName(name);
    //     term.setDescription(description);
    //     Term updatedTerm = termRepo.save(term);
    //     if (updatedTerm != null) System.out.println("Successfully updated");
    // }

    // public void deleteTermByName(String name) {
    //     termRepo.deleteById(name);
    // }
}
