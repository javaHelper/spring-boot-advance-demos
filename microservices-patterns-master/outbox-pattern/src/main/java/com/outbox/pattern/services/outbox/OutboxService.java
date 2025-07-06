package com.outbox.pattern.services.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.ExecutionException;

public interface OutboxService {
    void eventProcessing();
}
