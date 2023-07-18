package com.example.demo.publisher;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FoodOrderSource {

	// Defines methods for sending messages.
	@Output("foodOrdersChannel")
	MessageChannel foodOrders();
}