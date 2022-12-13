package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document
public class Freelancer {
    @Id
    private String id;
    private String name;
    private int age;
    private List<String> skills;
}