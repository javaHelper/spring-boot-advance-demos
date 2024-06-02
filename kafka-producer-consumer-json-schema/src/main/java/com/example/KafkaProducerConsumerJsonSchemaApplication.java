package com.example;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerConsumerJsonSchemaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerConsumerJsonSchemaApplication.class, args);
	}

	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Override
	public void run(String... args) throws Exception {
		Orders orders = Orders.builder()
				.orderId(ThreadLocalRandom.current().nextInt(1, 100))
				.itemId(1)
				.itemQuantity(ThreadLocalRandom.current().nextInt(1, 9))
				.price(ThreadLocalRandom.current().nextInt(500, 1000))
				.build();
		System.out.println("Sending Orders: "+ orders);
		kafkaProducer.sendMessage(orders);
	}

}
