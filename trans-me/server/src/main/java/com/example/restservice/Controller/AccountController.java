package com.example.restservice.Controller;

import java.util.List;
// import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.Model.Account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "test")
@RestController
public class AccountController {

	// private final static Quote NONE = new Quote("None");
	// private final static Random RANDOMIZER = new Random();

	// private final QuoteRepository repository;

	// public QuoteController(QuoteRepository repository) {
	// 	this.repository = repository;
	// }

	// @GetMapping("/api")
	// public List<QuoteResource> getAll() {

	// 	return repository.findAll().stream()
	// 		.map(quote -> new QuoteResource(quote, "success"))
	// 		.collect(Collectors.toList());
	// }

	@GetMapping("/")
	@Operation(summary = "get account", description = "get it！")
    public String getTest(@RequestParam String param) {
		
		// return new Account(null, param, param, null, null, null);
		return "Hello Swagger & test";
	}

	// @GetMapping("/account")
    // public Account getAccount(@RequestParam String param) {
		
	// 	return new Account(null, param, param, null, null, null);
	// }
        
    // public QuoteResource getOne(@PathVariable Long id) {
	// 	return repository.findById(id)
	// 		.map(quote -> new QuoteResource(quote, "success"))
	// 		.orElse(new QuoteResource(NONE, "Quote " + id + " does not exist"));
	// }

	// @GetMapping("/api/random")
	// public QuoteResource getRandomOne() {
	// 	return getOne(nextLong(1, repository.count() + 1));
	// }

	// private long nextLong(long lowerRange, long upperRange) {
	// 	return (long) (RANDOMIZER.nextDouble() * (upperRange - lowerRange)) + lowerRange;
	// }

    
    
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