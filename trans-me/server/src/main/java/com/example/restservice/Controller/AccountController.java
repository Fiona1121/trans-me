package com.example.restservice.Controller;

import java.lang.reflect.Constructor;
import java.util.List;
// import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.Model.Account;
import com.example.restservice.Repository.AccountRepository;
import com.example.restservice.Response.CommonResponse;
import com.example.restservice.Response.Msg;
import com.example.restservice.Service.Registration;
import com.example.restservice.Service.Payload.Payload;
import com.example.restservice.Service.Login;

import com.example.restservice.Request.account.PostAccountRequest;
import com.example.restservice.Request.account.GetAccountRequest;

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
	
	@Autowired
	Registration registration;

	@Autowired
	Login login;
	
	// private Account testAccount = new Account(
		
	// );
	
	// CRUD
	// 實務上到底是怎麼做，如果攔截 packet & 知道後端 API 的話就可以破解
	// 應該會 RSA 過

	// 例如 put，如果不帶 username 到底要怎麼 prevent 
	// 全用 camelCase ?

	// ToDo: 其實可以把 req & res 都改成 generic
	
	@GetMapping("/test")
	@Operation(summary = "get account", description = "get it！")
	public String getTest() {
		return "Hello account test";
	}
	
	// public class GetRequestBody {
	// 	public GetRequestData data;
	// }
	
	// public class GetRequestData {
	// 	public String username;
	// 	public String password;
	// }

	// @AllArgsConstructor
	// @NoArgsConstructor
	// public class GetResponseBody {
	// 	public Msg msg;
	// 	public GetResponseData data;
	// }

	// public class GetResponseData {
	// 	public String username;
	// 	public List<Id> audioFileIds;
	// 	public List<Id> blockIds;
	// }
	
	// Login
	// @GetMapping("/")
	@GetMapping("")
	public CommonResponse getAccount(@RequestBody GetAccountRequest req) {
		// TODO: GET request

		String username = req.getData().getUsername();
		String password = req.getData().getPassword();

		Payload <Msg, Account> loginResult =  login.login(username, password);
		
		System.out.println(loginResult);
		// return new CommonResponse<Account>(
		// 	loginResult.getMsg(),
		// 	loginResult.getData()
		// 	);
		
		CommonResponse<Account> test = new CommonResponse<Account>(
			loginResult.getMsg(),
			loginResult.getData()
			);

		System.out.println(test);
		return test;
	}
	
	// Register
	// @PostMapping("/")
	@PostMapping("")
	public CommonResponse postAccount(@RequestBody PostAccountRequest req) {

		String username = req.getData().getUsername();
		String password = req.getData().getPassword();

		// registration.register(username, password);
		
		return new CommonResponse<String>(
			registration.register(username, password),
			""
		);

	}

	// public class PutRequestBody {
	// 	public PutRequestData data;
	// }

	// public class PutRequestData {
	// 	public String username;
	// 	public List<Id> audioFileIds;
	// 	public List<Id> blockIds;
	// }

    // @PutMapping("/")
    // public PostResponseBody putAccount(@RequestBody PutRequestBody req) {
    //     //TODO: process PUT request
        
    //     return new PostResponseBody(

	// 	);
    // }

    
    
}