package com.outbox.pattern.repositories;

import com.outbox.pattern.entity.OrderEntity;
import com.outbox.pattern.utils.OrderStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {
    @Modifying
    @Query("""
                UPDATE OrderEntity o
                   SET o.status = :status
                 WHERE o.id = :id
            """)
    void updateStatus(@Param(value = "id") UUID id,
                      @Param(value = "status") OrderStatus status);
}
