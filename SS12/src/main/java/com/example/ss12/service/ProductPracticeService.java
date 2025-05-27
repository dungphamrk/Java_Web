package com.example.ss12.service;


import com.example.ss12.model.ProductPractice;

import java.util.List;

public interface ProductPracticeService {
    List<ProductPractice> findAll();
    ProductPractice findById(int id);
    void insert(ProductPractice product);
    void update(ProductPractice product);
    void delete(int id);
    List<ProductPractice> searchByName(String name);
}
