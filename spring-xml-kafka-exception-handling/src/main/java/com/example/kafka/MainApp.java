package com.example.kafka;

import com.example.kafka.model.Employee;
import com.example.kafka.producer.EmployeeProducer;
import com.github.javafaker.Faker;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ThreadLocalRandom;

public class MainApp {
    private static final Faker FAKER = Faker.instance().instance();

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("kafka-context.xml");
        ctx.registerShutdownHook();

        EmployeeProducer employeeProducer = (EmployeeProducer) ctx.getBean("employeeProducer");

        Employee employee = Employee.builder()
                .empId(ThreadLocalRandom.current().nextInt(1, 100))
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .gender(getGender())
                .build();

        employeeProducer.sendMessage(employee);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getGender() {
        int ramdomN = ThreadLocalRandom.current().nextInt(0, 1);
        String sex;
        if (ramdomN == 0) {
            sex = "M";
        } else {
            sex = "F";
        }
        return sex;
    }
}