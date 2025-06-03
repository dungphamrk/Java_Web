package com.data.ss17.service;

import com.data.ss17.model.ProductCart;
import com.data.ss17.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartService {
    @Autowired
    private ProductCartRepository productCartRepository;

    public List<ProductCart> getCartByCustomerId(Long customerId) {
        return productCartRepository.getCartByCustomerId(customerId);
    }

    public void addOrUpdateCart(ProductCart cart) {
        productCartRepository.addOrUpdateCart(cart);
    }

    public void deleteCart(Long id) {
        productCartRepository.deleteCart(id);
    }

    public ProductCart getCartByCustomerIdAndProductId(Long customerId, Long productId) {
        return productCartRepository.getCartByCustomerIdAndProductId(customerId, productId);
    }
}