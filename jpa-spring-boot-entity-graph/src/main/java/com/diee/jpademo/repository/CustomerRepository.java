package com.diee.jpademo.repository;

import com.diee.jpademo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, BaseRepository<Customer, Integer> {

}
