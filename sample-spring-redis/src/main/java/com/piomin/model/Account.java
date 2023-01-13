package com.piomin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    @Indexed
    private Long id;
    private String number;
    private int balance;
}