package com.diee.jpademo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomerWithOrdersDto extends CustomerDto {

    private List<OrderDto> orders;

    public CustomerWithOrdersDto() {
        super();
    }

}
