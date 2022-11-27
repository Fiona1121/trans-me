package com.example.restservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.Model.Account;
import com.example.restservice.Repository.AccountRepository;
import com.example.restservice.Request.account.PutAccountRequest;
import com.example.restservice.Response.Msg;
import com.example.restservice.Service.Payload.Payload;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Payload <Msg, Account> update(PutAccountRequest.RequestData data) {
        
        System.out.println("Update account：");
        
        // if (newAccount.getUsername() == null) {
        if (data.getUsername() == null) { // 這樣是不是就用不到這個 case 了？
            System.out.println("invalid username");

            return new Payload <Msg, Account> (
                new Msg(
                    "error",
                    "invalid username"
                ),
                null
            );
        }
        else {
            System.out.println("username = "+ data.getUsername());

            Account DBAccount = accountRepository.findByUsername(data.getUsername());
            if (DBAccount == null) {
                System.out.println("username not found");

                return new Payload <Msg, Account> (
                    new Msg(
                        "error",
                        "username not found"
                    ),
                    null
                );
            }
            else { // update
                String updatedTerms = "";
                
                // currently don't support
                // if (newAccount.getPassword() != null) {
                //     DBAccount.setPassword(newAccount.getPassword());
                //     updatedTerms += "password, ";
                // }
                
                if (data.getAudioFilesId() != null) {
                    DBAccount.setAudioFilesID(data.getAudioFilesId());
                    updatedTerms += "aduioFiles, ";
                }
                if (data.getBlocksId() != null) {
                    DBAccount.setBlocksID(data.getBlocksId());
                    updatedTerms += "blocks ";
                }

                accountRepository.save(DBAccount);
                System.out.println("updatedTerms : " + updatedTerms);

                return new Payload <Msg, Account> (
                    new Msg(
                       "success",
                       "return user data"
                    ),
                    DBAccount
                );
            }
        }

    }
    
}    


        
