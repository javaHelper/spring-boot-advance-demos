package com.diee.jpademo.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "orderdetails")
public class OrderDetail implements Serializable {

    @EmbeddedId
    private OrderDetailProductId id;

    @MapsId("orderNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderNumber")
    private Order order;

    @MapsId("productCode")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode", referencedColumnName = "productCode")
    private Product product;

    private Integer quantityOrdered;
    private BigDecimal priceEach;
    private Integer orderLineNumber;


    public OrderDetail(){

    }

    public OrderDetail(Order order, Product product){
        this.order = order;
        this.product = product;
        this.id = new OrderDetailProductId(order.getOrderNumber(), product.getProductCode());
    }

    public OrderDetail(OrderDetailProductId id) {
        this.id = id;
    }


    public OrderDetailProductId getId() {
        return id;
    }

    public void setId(OrderDetailProductId orderDetailProductId) {
        this.id = orderDetailProductId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return id.equals(that.id) &&
                order.equals(that.order) &&
                product.equals(that.product) &&
                quantityOrdered.equals(that.quantityOrdered) &&
                priceEach.equals(that.priceEach) &&
                orderLineNumber.equals(that.orderLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, quantityOrdered, priceEach, orderLineNumber);
    }
}
