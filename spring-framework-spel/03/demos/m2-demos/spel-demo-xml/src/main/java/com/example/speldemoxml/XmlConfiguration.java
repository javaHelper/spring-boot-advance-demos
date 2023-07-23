package com.example.speldemoxml;

import com.example.speldemoxml.data.City;
import com.example.speldemoxml.data.Order;
import com.example.speldemoxml.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Iterator;
import java.util.List;


@Configuration
@ImportResource({"classpath*:applicationContext.xml"})
public class XmlConfiguration {

    @Autowired
    User user;

    @Autowired
    Order order;

    @Autowired
    Order order2;

    @Autowired
    Order order3;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                XmlConfiguration.class);
        try {
            XmlConfiguration xmlConfiguration = (XmlConfiguration) context.getBean("xmlConfiguration");
            System.out.println("Customer Name: " + xmlConfiguration.getUser().getName());
            System.out.println("Age: " + xmlConfiguration.getUser().getAge());
            System.out.println("Country: " + xmlConfiguration.getUser().getCountry());
            System.out.println("Order Amount: " + xmlConfiguration.getOrder().getAmount());
            System.out.println("Discount: " + xmlConfiguration.getOrder2().getDiscount());
            System.out.println("Days to deliver: " + xmlConfiguration.getOrder2().getDaysToDeliver());
            System.out.println("Formatted Amount: " + xmlConfiguration.getOrder2().getFormattedAmount());
            System.out.println("Shipping Locations: " );
            for(City city : xmlConfiguration.getOrder2().getShippingLocations()) {
                System.out.println(city.getName());
            }

            System.out.println("Western Shipping Locations: " );
            for(Iterator i = xmlConfiguration.getOrder2().getWesternShippingLocations().values().iterator(); i.hasNext();) {
                List<City> cities = (List<City>)i.next();
                for(City city : cities) {
                    System.out.println(city.getName());
                }
            }

            System.out.println("Non Capital Shipping Locations: " );
            for(City city : xmlConfiguration.getOrder3().getNonCapitalShippingLocations()) {
                System.out.println(city.getName());
            }

            System.out.println("Cheap Shipping Locations: " + xmlConfiguration.getOrder3().getNoOfCheapShippingLocations());

            System.out.println("Order Summary " + xmlConfiguration.getOrder3().getOrderSummary());

        } finally {
            context.close();
        }
    }

    public User getUser() {
        return user;
    }

    public Order getOrder() {
        return order;
    }

    public Order getOrder2() {
        return order2;
    }

    public Order getOrder3() {
        return order3;
    }
}