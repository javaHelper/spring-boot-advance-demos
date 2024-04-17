package com.example.kafka;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyMainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        ctx.registerShutdownHook();

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}