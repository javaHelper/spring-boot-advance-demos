package com.example.kafka.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class MyMessageListener implements MessageListener<String, Object> {
    private static final Logger LOG = LoggerFactory.getLogger(MyMessageListener.class);

    @Override
    public void onMessage(ConsumerRecord<String, Object> data) {
        LOG.info("##### Consuming Message ########");
        LOG.info("Message # {}", data.value());
        LOG.info("####################");
    }
}