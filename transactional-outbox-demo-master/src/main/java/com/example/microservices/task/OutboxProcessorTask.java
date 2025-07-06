package com.example.microservices.task;

import com.example.microservices.entity.Outbox;
import com.example.microservices.entity.User;
import com.example.microservices.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OutboxProcessorTask {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;
    private final ObjectMapper objectMapper;

    public OutboxProcessorTask(OutboxRepository outboxRepository,
                               KafkaTemplate<String, Object> kafkaTemplate,
                               @Value("${kafka.topic.user}") String topic, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void process() throws JsonProcessingException {

        List<Outbox> outboxes = outboxRepository.findTop10ByIsDelivered(false);
        if(!outboxes.isEmpty()) {
            System.out.println("Task executed");
            for (Outbox outbox : outboxes) {
                kafkaTemplate.send(topic,  objectMapper.readValue(outbox.getMessage(), User.class));
                outbox.setIsDelivered(true);
            }
        }
    }
}
