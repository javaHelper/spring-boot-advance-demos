package com.diee.jpademo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderWithDetailDto extends OrderDto {
    private List<OrderDetailDto> orderDetails;

    public OrderWithDetailDto() {
        super();
    }
}
