package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Orders {
	private Integer itemId;
	private Integer itemQuantity;
	private Integer orderId;
	private Integer price;
}
