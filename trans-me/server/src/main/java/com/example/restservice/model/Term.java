package com.example.restservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("terms")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Term {
    @Id
    private String name;
    private String link;
    private String description;
}