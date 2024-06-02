package com.example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@KafkaListener(topics = {"orders"})
	public void listen(Orders orders) {
		System.out.println("-----------------------");
		System.out.println(orders);
	}
}
