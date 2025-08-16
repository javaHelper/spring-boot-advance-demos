package com.diee.jpademo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderDetailDto {
    private String productCode;
    private Integer quantityOrdered;
    private Number priceEach;
    private Integer orderLineNumber;
}
