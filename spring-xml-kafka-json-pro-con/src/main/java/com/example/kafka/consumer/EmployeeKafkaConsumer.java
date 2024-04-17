package com.example.kafka.consumer;

import com.example.kafka.config.AppConfig;
import com.example.kafka.listener.MyMessageListener;
import com.example.kafka.model.Employee;
import com.example.kafka.serdes.JsonDeserializer;
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
public class EmployeeKafkaConsumer {

    public EmployeeKafkaConsumer(String topic) {
        initializeConsumer(topic);
    }

    private void initializeConsumer(String topic) {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfig.CLIENT_ID);
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVER);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        propsMap.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Employee.class);


        DefaultKafkaConsumerFactory<String, String> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(
                propsMap,
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