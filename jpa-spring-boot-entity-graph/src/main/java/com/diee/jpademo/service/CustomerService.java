package com.diee.jpademo.service;

import com.diee.jpademo.model.Customer;
import com.diee.jpademo.model.CustomerRelation;
import com.diee.jpademo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getById(Integer id) {
        return this.customerRepository.getReferenceById(id);
    }

    public Customer getCustomerWithGraphById(Integer id, CustomerRelation relation) {
        return this.customerRepository.findWithGraph(id, relation.getName());
    }
}
