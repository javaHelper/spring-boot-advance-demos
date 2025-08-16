package com.diee.jpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerDto {
    private Integer customerNumber;
    private String customerName;
    private String phone;
    private String city;
    private String postalCode;
    private String country;
    private Number creditLimit;
}
