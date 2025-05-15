package com.data.ss8.repository.Bai2;

import com.data.ss8.model.Bai2.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
