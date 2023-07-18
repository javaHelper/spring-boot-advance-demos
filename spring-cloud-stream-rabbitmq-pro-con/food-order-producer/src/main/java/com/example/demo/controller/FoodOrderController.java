package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FoodOrder;
import com.example.demo.publisher.FoodOrderSource;

@RestController
public class FoodOrderController {

	@Autowired
	FoodOrderSource foodOrderSource;

	@RequestMapping("/order")
	@ResponseBody
	public String orderFood(@RequestBody FoodOrder foodOrder) {
		foodOrderSource.foodOrders().send(MessageBuilder.withPayload(foodOrder).build());
		System.out.println(foodOrder.toString());
		return "food ordered!";
	}
}