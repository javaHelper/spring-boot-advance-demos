package com.example.kafka.producer;

import com.example.kafka.config.AppConfig;
import com.example.kafka.model.Employee;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;

public class EmployeeProducer {
    Properties properties;

    public EmployeeProducer(){
        initializeProducer();
    }

    private void initializeProducer() {
        properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfig.CLIENT_ID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    }

    public void sendMessage(Employee employee){

        // Way-1
        KafkaProducer<String, Employee> kafkaProducer = new KafkaProducer<>(properties);
        kafkaProducer.send(new ProducerRecord<>(AppConfig.TOPIC_NAME, ""+employee.getEmpId(), employee));
        System.out.println(">> Producer send the message : "+ employee);

       // ProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(configs);
       // this.kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }
}