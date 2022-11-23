package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;
// 啥?

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // returns a domain object instead of a view ?
// shorthand for including both @Controller and @ResponseBody
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}

// @Controller 的話就可以傳回 html page