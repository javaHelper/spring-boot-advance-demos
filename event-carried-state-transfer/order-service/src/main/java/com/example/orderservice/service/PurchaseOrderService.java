package com.example.orderservice.service;


import java.util.List;

import com.example.orderservice.entity.PurchaseOrder;

public interface PurchaseOrderService {
    List<PurchaseOrder> getPurchaseOrders();
    void createPurchaseOrder(PurchaseOrder purchaseOrder);
}
