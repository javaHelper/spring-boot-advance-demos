package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor(staticName = "of")
public class Account implements Serializable {
    private int userId;
    private int balance;
}