package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Orders> kafkaTemplate;
	
	public void sendMessage(Orders orders) {
		kafkaTemplate.send("orders",orders);
	}
}
