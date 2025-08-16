package com.diee.jpademo.repository;

import com.diee.jpademo.model.Customer;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements BaseRepository<Customer, Integer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer findWithGraph(Integer id, String graphName) {

        EntityGraph<?> entityGraph = entityManager.getEntityGraph(graphName);

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);

        return entityManager.find(Customer.class, id, properties);
    }
}
