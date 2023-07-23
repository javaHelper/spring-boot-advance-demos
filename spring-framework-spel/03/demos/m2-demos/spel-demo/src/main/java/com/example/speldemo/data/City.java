package com.example.speldemo.data;

import org.springframework.stereotype.Component;

@Component("city")
public class City {
    private String name;
    private double shipping;
    private Boolean isCapital;

    public City() {
    }

    public City(String name, double shipping, Boolean isCapital) {
        this.name = name;
        this.shipping = shipping;
        this.isCapital = isCapital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public Boolean getIsCapital() {
        return isCapital;
    }

    public void setIsCapital(Boolean capital) {
        isCapital = capital;
    }
}
