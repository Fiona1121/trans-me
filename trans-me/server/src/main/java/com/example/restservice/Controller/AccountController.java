package com.example.restservice.Controller;

import java.util.List;
// import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


// @Controller 的話就可以傳回 html page

@Tag(name = "test")
@RestController
@RequestMapping("/account")
public class AccountController {
	
	// private Account testAccount = new Account(
		
	// );
	
	// CRUD
	// 實務上到底是怎麼做，如果攔截 packet & 知道後端 API 的話就可以破解
	// 應該會 RSA 過

	// 例如 put，如果不帶 username 到底要怎麼 prevent 
	// 全用 camelCase ?

	public class loginInfo {
		public String username;
		public String password;
	}

	@GetMapping("/test")
	@Operation(summary = "get account", description = "get it！")
    public String getTest() {
		return "Hello account test";
	}
	
	@GetMapping("/")
	public Account geAccount(@RequestBody loginInfo info) {
		
		return new Account(

		);

	}

	

    // @PutMapping("/account/{id}")
    // public SomeEnityData putMethodName(@PathVariable String id, @RequestBody SomeEnityData entity) {
    //     //TODO: process PUT request
        
    //     return entity;
    // }

    // @PostMapping("/account")
    // public SomeEnityData postMethodName(@RequestBody SomeEnityData entity) {
    //     //TODO: process POST request
        
    //     return entity;
    // }
    
    
}