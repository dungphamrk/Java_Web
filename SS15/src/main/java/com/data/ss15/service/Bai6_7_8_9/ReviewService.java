package com.data.ss15.service.Bai6_7_8_9;

import com.data.ss15.model.Bai6_7_8_9.*;
import com.data.ss15.model.Bai6_7_8_9.Product;
import com.data.ss15.model.Bai6_7_8_9.Review;

import java.util.List;

public interface ReviewService {
    boolean addReview(Review review);
    List<Product> getAllProducts();
    List<Review> getReviewsByProductId(int productId);
    Product getProductById(int id);
    boolean addToCart(Cart cart);
    List<Cart> getCartByUserId(int userId);
    boolean addOrder(Orders orders);
}
