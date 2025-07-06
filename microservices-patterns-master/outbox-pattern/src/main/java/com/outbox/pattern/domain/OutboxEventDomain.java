package com.outbox.pattern.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outbox.pattern.utils.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboxEventDomain {
    private OrderStatus eventType;
    private String eventPayload;
}
