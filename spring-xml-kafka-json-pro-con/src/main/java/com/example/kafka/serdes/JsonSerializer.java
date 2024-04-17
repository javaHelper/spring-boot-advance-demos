package com.example.kafka.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonSerializer<T> implements Serializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonSerializer() {

    }

    @Override
    public void configure(Map<String, ?> config, boolean isKey) {
        //Nothing to Configure
    }

    /**
     * Serialize JsonNode
     *
     * @param topic Kafka topic name
     * @param data  data as JsonNode
     * @return byte[]
     */
    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }

    @Override
    public void close() {

    }
}