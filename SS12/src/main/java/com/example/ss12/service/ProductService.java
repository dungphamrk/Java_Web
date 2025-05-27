package com.example.ss12.service;


import com.example.ss12.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    void insert(Product product);
    void update(Product product);
    void delete(int id);
}

