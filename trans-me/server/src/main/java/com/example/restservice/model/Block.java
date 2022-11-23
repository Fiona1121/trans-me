package com.example.restservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Block {
    @Id
    private String id;
    
    private String content;
    private boolean hidden;
}