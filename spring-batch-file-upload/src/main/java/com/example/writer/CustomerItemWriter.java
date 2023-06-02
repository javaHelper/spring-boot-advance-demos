package com.example.writer;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerItemWriter implements ItemWriter<Customer> {
    @Autowired
    private CustomerRepository repository;

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        System.out.println("Writer Thread " + Thread.currentThread().getName());
        repository.saveAll(items);
    }
}