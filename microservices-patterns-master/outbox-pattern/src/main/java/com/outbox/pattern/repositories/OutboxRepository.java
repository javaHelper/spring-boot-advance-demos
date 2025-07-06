package com.outbox.pattern.repositories;

import com.outbox.pattern.entity.OutboxEventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboxRepository extends CrudRepository<OutboxEventEntity, UUID> {
    //Empty Interface
}
