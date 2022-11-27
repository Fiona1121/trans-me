package com.example.restservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "index")
@RestController
public class index {
    
    @GetMapping("/")
    @Operation(summary = "get index", description = "get it！")
    public String getIndex() {
        
        // return new Account(null, param, param, null, null, null);
        // return "Hello " + param;
        return "Hello ";
    }
}

