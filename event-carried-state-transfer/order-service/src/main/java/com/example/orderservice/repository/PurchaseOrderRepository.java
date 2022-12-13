package com.example.orderservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.orderservice.entity.PurchaseOrder;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, ObjectId> {

    @Query("{ 'user.id': ?0 }")
    List<PurchaseOrder> findByUserId(Long userId);

}
