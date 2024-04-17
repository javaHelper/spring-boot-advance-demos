package com.example.kafka.consumer;

import com.example.kafka.config.AppConfig;
import com.example.kafka.listener.MyMessageListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 *  Consumer Config class
 */
public class ConfluentKafkaConsumer {

    public ConfluentKafkaConsumer(String topic) {
        initializeConsumer(topic);
    }

    private void initializeConsumer(String topic) {
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.CLIENT_ID_CONFIG, "myClientId");
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVER);
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, AppConfig.GROUP_NAME);

        DefaultKafkaConsumerFactory<String, String> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(
                consumerConfig,
                new StringDeserializer(),
                new StringDeserializer()
        );

        ContainerProperties containerProperties = new ContainerProperties(topic);
        containerProperties.setMessageListener(new MyMessageListener());

        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(
            kafkaConsumerFactory,
            containerProperties
        );
        container.start();
     }
}