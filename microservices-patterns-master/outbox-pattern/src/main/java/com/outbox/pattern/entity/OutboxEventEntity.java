package com.outbox.pattern.entity;

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
@Entity
@Table(name = "outbox")
public class OutboxEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "event_type", nullable = false)
    private OrderStatus eventType;

    @Column(name ="event_payload", nullable = false)
    private String eventPayload;
}
