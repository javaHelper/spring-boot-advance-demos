package com.example.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class FoodOrderConsumer {
	
	@StreamListener(target = Sink.INPUT)
	public void processCheapMeals(String meal) throws Exception {
		if (meal.contains("vegetables"))
			throw new Exception("Vegetables! Move to dead letter queue!");
		if (meal.contains("poison"))
			throw new Exception("Poison! Move to dead letter queue!");
		
		System.out.println("Meal consumed: " + meal);
	}
}
