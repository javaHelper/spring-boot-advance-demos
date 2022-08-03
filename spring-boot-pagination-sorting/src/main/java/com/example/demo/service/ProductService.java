package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findProducts() {
        return repository.findAll();
    }

    public List<Product> findProductsWithSorting(String field) {
        final Sort sort = Sort.by(Sort.Direction.ASC, field);
        return repository.findAll(sort);
    }

    public Page<Product> findProductsWithPagination(int offset, int pageSize) {
        final PageRequest pageRequest = PageRequest.of(offset, pageSize);
        return repository.findAll(pageRequest);
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field) {
        final PageRequest pageRequest = PageRequest.of(offset, pageSize)
                .withSort(Sort.by(field));

        return repository.findAll(pageRequest);
    }
}