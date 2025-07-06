package com.outbox.pattern.services.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.outbox.pattern.entity.OutboxEventEntity;
import com.outbox.pattern.repositories.OutboxRepository;
import com.outbox.pattern.utils.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {
    private static final Logger LOG = LoggerFactory.getLogger(OutboxServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Value("${configuration.kafka.outbox-topic}")
    private String outboxTopic;

    @Override
    @Scheduled(fixedRateString = "${configuration.kafka.scheduled}")
    public void eventProcessing() {
        List<OutboxEventEntity> listOfOutboxEventEntities = new ArrayList<>();
        outboxRepository.findAll().forEach(listOfOutboxEventEntities::add);
        LOG.info("Number of outbox events: {}", listOfOutboxEventEntities.size());

        if (!listOfOutboxEventEntities.isEmpty()) {
            for (OutboxEventEntity outboxEventEntity : listOfOutboxEventEntities) {
                LOG.info("Sending event to Kafka");
                String eventType = determineEventType(outboxEventEntity.getEventType());
                if (eventType != null) {
                    sendEventToKafka(eventType, outboxEventEntity);
                }
                outboxRepository.deleteById(outboxEventEntity.getId());
            }
        }
    }

    private String determineEventType(OrderStatus eventType) {
        return switch (eventType) {
            case CREATED -> "CREATED";
            case CANCELLED -> "CANCELLED";
        };
    }

    private void sendEventToKafka(String eventType, OutboxEventEntity outboxEventEntity) {
        try {
            CompletableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(outboxTopic, eventType, objectMapper.writeValueAsString(outboxEventEntity));
            SendResult<String, String> result = sendResult.get();
            LOG.info("Partition: {}", result.getRecordMetadata().partition());
        } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
            LOG.error("Error sending event to Kafka: {}", e.getMessage());
        }
    }
}