package com.data.ss17.service;

import com.data.ss17.model.Product;
import com.data.ss17.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(int page, int pageSize) {
        return productRepository.getProducts(page, pageSize);
    }

    public long getTotalProducts() {
        return productRepository.getTotalProducts();
    }

    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepository.getProductsByIds(productIds);
    }

    public Product saveProduct(Product product) {
        productRepository.saveProduct(product);
        return product;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice, Integer minStock, int page, int pageSize) {
        return productRepository.filterProducts(name, minPrice, maxPrice, minStock, page, pageSize);
    }
}