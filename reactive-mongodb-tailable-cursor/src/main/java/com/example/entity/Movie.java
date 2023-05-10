package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@Document
@ToString
public class Movie {

    @Id
    private String id;
    private String title;
    private int year;
    private String genre;
    private double imdbRating;
}