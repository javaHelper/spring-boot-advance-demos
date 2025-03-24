package com.example.eventpublisher;

import com.example.event.CustomerEvent;
import com.example.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishEvent(Customer customer){
        log.info("Calling EventPublisher | publishEvent");
        CustomerEvent customerEvent = new CustomerEvent(this, customer);
        publisher.publishEvent(customerEvent);
    }

    public void publishEventObject(Customer customer){
        log.info("---------------------------");
        publisher.publishEvent(customer);
    }
}