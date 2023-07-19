package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FoodOrderDlqProcessor {
	private static final String ORIGINAL_QUEUE = "foodOrders.foodOrdersIntakeGroup";

	private static final String DLQ = ORIGINAL_QUEUE + ".dlq";

	private static final String X_RETRIES_HEADER = "x-retries";

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RabbitListener(queues = DLQ)
	public void rePublish(Message failedMessage) {

		failedMessage = attemptToRepair(failedMessage);

		Integer retriesHeader = (Integer) failedMessage.getMessageProperties().getHeaders().get(X_RETRIES_HEADER);
		if (retriesHeader == null) {
			retriesHeader = Integer.valueOf(0);
		}
		if (retriesHeader < 3) {
			failedMessage.getMessageProperties().getHeaders().put(X_RETRIES_HEADER, retriesHeader + 1);
			this.rabbitTemplate.send(ORIGINAL_QUEUE, failedMessage);
		}
		else {
			System.out.println("Writing to databse: "+failedMessage.toString());
			//we can write to a database or move to a parking lot queue
		}
	}

	private Message attemptToRepair(Message failedMessage) {
		String messageBody = new String(failedMessage.getBody());

		if(messageBody.contains("vegetables")) {
			System.out.println("Repairing message: "+failedMessage.toString());
			messageBody = messageBody.replace("vegetables", "cakes");
			return MessageBuilder.withBody(messageBody.getBytes()).copyHeaders(failedMessage.getMessageProperties().getHeaders()).build();
		}
		return failedMessage;
	}
}
