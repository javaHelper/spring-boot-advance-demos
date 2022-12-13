package com.example.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderservice.entity.PurchaseOrder;
import com.example.orderservice.repository.PurchaseOrderRepository;
import com.example.orderservice.service.PurchaseOrderService;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> getPurchaseOrders() {
        return this.purchaseOrderRepository.findAll();
    }

    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrderRepository.save(purchaseOrder);
    }

}
