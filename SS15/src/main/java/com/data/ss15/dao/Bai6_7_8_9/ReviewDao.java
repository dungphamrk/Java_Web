package com.data.ss15.dao.Bai6_7_8_9;

import com.data.ss15.model.Bai6_7_8_9.Cart;
import com.data.ss15.model.Bai6_7_8_9.Orders;
import com.data.ss15.model.Bai6_7_8_9.Product;
import com.data.ss15.model.Bai6_7_8_9.Review;

import java.util.List;

public interface ReviewDao {
    boolean addReview(Review review);
    List<Product> getAllProducts();
    List<Review> getReviewsByProductId(int productId);
    Product getProductById(int id);
    boolean addToCart(Cart cart);
    List<Cart> getCartByUserId(int userId);
    boolean addOrder(Orders orders);
}
