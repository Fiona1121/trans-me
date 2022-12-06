package com.example.restservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.Response.Msg;
import com.example.restservice.Model.Account;
import com.example.restservice.Repository.AccountRepository;

@Service
public class Registration {
    
    @Autowired
    AccountRepository accountRepository;

    public Msg register(String username, String password) {
        System.out.println("Register：");
        System.out.println("username = "+username);
        if (accountRepository.existsByUsername(username)) {
            System.out.println("error");
            return new Msg(
                "error",
                "user with the same username already exists"
            );
        }
        else {
            accountRepository.save(new Account(
                username, password
            ));
            System.out.println("success");
            return new Msg(
                "success",
                "new acount created"
            );
        }
        
    }
}
