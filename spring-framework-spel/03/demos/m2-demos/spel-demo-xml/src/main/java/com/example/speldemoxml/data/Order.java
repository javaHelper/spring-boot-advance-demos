package com.example.speldemoxml.data;

import java.util.List;
import java.util.Map;

public class Order {
    private double amount;
    private double discount;
    private int daysToDeliver;
    private String origin;
    private String formattedAmount;
    private List<City> shippingLocations;
    private List<City> nonCapitalShippingLocations;
    private Map<String,List<City>> westernShippingLocations;
    private Integer noOfCheapShippingLocations;
    private String orderSummary;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getDaysToDeliver() {
        return daysToDeliver;
    }

    public void setDaysToDeliver(int daysToDeliver) {
        this.daysToDeliver = daysToDeliver;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFormattedAmount() {
        return formattedAmount;
    }

    public void setFormattedAmount(String formattedAmount) {
        this.formattedAmount = formattedAmount;
    }

    public List<City> getShippingLocations() {
        return shippingLocations;
    }

    public void setShippingLocations(List<City> shippingLocations) {
        this.shippingLocations = shippingLocations;
    }

    public List<City> getNonCapitalShippingLocations() {
        return nonCapitalShippingLocations;
    }

    public void setNonCapitalShippingLocations(List<City> nonCapitalShippingLocations) {
        this.nonCapitalShippingLocations = nonCapitalShippingLocations;
    }

    public Integer getNoOfCheapShippingLocations() {
        return noOfCheapShippingLocations;
    }

    public void setNoOfCheapShippingLocations(Integer noOfCheapShippingLocations) {
        this.noOfCheapShippingLocations = noOfCheapShippingLocations;
    }

    public Map<String, List<City>> getWesternShippingLocations() {
        return westernShippingLocations;
    }

    public void setWesternShippingLocations(Map<String, List<City>> westernShippingLocations) {
        this.westernShippingLocations = westernShippingLocations;
    }

    public String getOrderSummary() {
        return orderSummary;
    }

    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }
}
