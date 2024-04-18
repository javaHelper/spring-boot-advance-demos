package com.example.kafka.consumer;

import com.example.kafka.config.AppConfig;
import com.example.kafka.listener.MyMessageListener;
import com.example.kafka.model.Employee;
import com.example.kafka.serdes.AppJsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 *  Consumer Config class
 *  https://docs.spring.io/spring-kafka/docs/2.2.4.RELEASE/reference/#error-handling-deserializer
 */
public class EmployeeKafkaConsumer2 {

    public EmployeeKafkaConsumer2(String topic) {
        initializeConsumer(topic);
    }

    private void initializeConsumer(String topic) {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfig.CLIENT_ID);
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVER);

        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        propsMap.put(AppJsonDeserializer.VALUE_CLASS_NAME_CONFIG, Employee.class);

        DefaultKafkaConsumerFactory<String, Employee> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(
                propsMap,
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new AppJsonDeserializer<>())
        );

        ContainerProperties containerProperties = new ContainerProperties(topic);
        containerProperties.setMessageListener(new MyMessageListener());

        ConcurrentMessageListenerContainer<String, Employee> container =
                new ConcurrentMessageListenerContainer<>(kafkaConsumerFactory, containerProperties);
        container.start();
    }
}