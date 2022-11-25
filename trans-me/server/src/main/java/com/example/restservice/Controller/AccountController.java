package com.example.restservice.Controller;

<<<<<<< Updated upstream
=======
import java.lang.reflect.Constructor;
>>>>>>> Stashed changes
import java.util.List;
// import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

<<<<<<< Updated upstream
=======
import org.springframework.data.annotation.Id;
>>>>>>> Stashed changes
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

<<<<<<< Updated upstream
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


// @Controller 的話就可以傳回 html page

@Tag(name = "test")
@RestController
@RequestMapping("/account")
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
public class AccountController {
	
	// private Account testAccount = new Account(
		
	// );
	
	// CRUD
	// 實務上到底是怎麼做，如果攔截 packet & 知道後端 API 的話就可以破解
	// 應該會 RSA 過

	// 例如 put，如果不帶 username 到底要怎麼 prevent 
	// 全用 camelCase ?

<<<<<<< Updated upstream
	public class loginInfo {
=======
	// ToDo: 其實可以把 req & res 都改成 generic
	
	@GetMapping("/test")
	@Operation(summary = "get account", description = "get it！")
	public String getTest() {
		return "Hello account test";
	}
	
	public class GetRequestBody {
		public GetRequestData data;
	}
	
	public class GetRequestData {
>>>>>>> Stashed changes
		public String username;
		public String password;
	}

<<<<<<< Updated upstream
	@GetMapping("/test")
	@Operation(summary = "get account", description = "get it！")
    public String getTest() {
		return "Hello account test";
	}
	
	@GetMapping("/")
	public Account geAccount(@RequestBody loginInfo info) {
		
		return new Account(
=======
	@AllArgsConstructor
	@NoArgsConstructor
	public class GetResponseBody {
		public Msg msg;
		public GetResponseData data;
	}

	public class GetResponseData {
		public String username;
		public List<Id> audioFileIds;
		public List<Id> blockIds;
	}
	
	@GetMapping("/")
	public GetResponseBody getAccount(@RequestBody GetRequestBody req) {
		// TODO: GET request

		String username = req.data.username;
		String password = req.data.password;

		return new GetResponseBody(
			new Msg(
				"success",
				"OK"
			),
			new GetResponseData(

			)
>>>>>>> Stashed changes

		);

	}

<<<<<<< Updated upstream
	

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
=======
	public class PostResponseBody {
		public Msg msg;
	}

	@PostMapping("/")
	public PostResponseBody postAccount(@RequestBody GetRequestBody req) {
		// 直接用 GET 要小心
	    // TODO: process POST request

		String username = req.data.username;
		String password = req.data.password;

		
	    return new PostResponseBody(
			
		);
	}
	
	public class PutRequestBody {
		public PutRequestData data;
	}

	public class PutRequestData {
		public String username;
		public List<Id> audioFileIds;
		public List<Id> blockIds;
	}

    @PutMapping("/")
    public PostResponseBody putAccount(@RequestBody PutRequestBody req) {
        //TODO: process PUT request
        
        return new PostResponseBody(

		);
    }

>>>>>>> Stashed changes
    
    
}