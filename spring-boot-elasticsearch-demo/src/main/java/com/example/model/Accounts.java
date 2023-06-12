package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "accounts", createIndex = false)
public class Accounts {
    @Id
    private String id;
    private Long account_number;
    private Long balance;
    private String firstname;
    private String lastname;
    private Integer age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}