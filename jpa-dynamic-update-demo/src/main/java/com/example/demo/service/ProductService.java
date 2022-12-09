package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product){
        return repository.save(product);
    }

    public Product getProduct(int id) {
        return repository.findById(id).get();
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product deleted";
    }

    public Product updateProduct(int id, Product productRequest) {
        Product existingProduct = repository.findById(id).get(); // DB
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());

        return repository.save(existingProduct);
    }
}