package com.example.demo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Review {
    private String user;
    private Integer rating;
    private String comment;
}