package com.example.kafka.listener;

import com.example.kafka.model.Employee;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class MyMessageListener implements MessageListener<String, Employee> {
    private static final Logger LOG = LoggerFactory.getLogger(MyMessageListener.class);

    @Override
    public void onMessage(ConsumerRecord<String, Employee> record) {
        LOG.info("##### Consuming Message ########");
        LOG.info("Topic: {}, Partition: {}, Offset: {}, Message # {}", record.topic()
                , record.partition(), record.offset()
                , record.value());
        Employee employee = record.value();
        System.out.println("Employee : " + employee);
        LOG.info("####################");
    }
}