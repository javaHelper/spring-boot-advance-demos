package com.example.listener;

import com.example.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

public class StepSkipListener implements SkipListener<Customer, Number> {
    private Logger logger = LoggerFactory.getLogger(StepSkipListener.class);

    @Override
    public void onSkipInRead(Throwable t) {
        logger.info("A failure on read {} ", t.getMessage());
    }

    @Override
    public void onSkipInWrite(Number item, Throwable t) {
        logger.info("A failure on write {} , {}", t.getMessage(), item);
    }

    @SneakyThrows
    @Override
    public void onSkipInProcess(Customer customer, Throwable t) {
        logger.info("Item {}  was skipped due to the exception  {}", new ObjectMapper().writeValueAsString(customer),
                t.getMessage());
    }
}