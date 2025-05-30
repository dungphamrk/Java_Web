package com.data.ss15.service.Bai6_7_8_9;

import com.data.ss15.dao.Bai6_7_8_9.ReviewDao;
import com.data.ss15.model.Bai6_7_8_9.Cart;
import com.data.ss15.model.Bai6_7_8_9.Orders;
import com.data.ss15.model.Bai6_7_8_9.Product;
import com.data.ss15.model.Bai6_7_8_9.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public boolean addReview(Review review) {
        return reviewDao.addReview(review);
    }

    @Override
    public List<Product> getAllProducts() {
        return reviewDao.getAllProducts();
    }

    @Override
    public List<Review> getReviewsByProductId(int productId) {
        return reviewDao.getReviewsByProductId(productId);
    }

    @Override
    public Product getProductById(int id) {
        return reviewDao.getProductById(id);
    }

    @Override
    public boolean addToCart(Cart cart) {
        return reviewDao.addToCart(cart);
    }

    @Override
    public List<Cart> getCartByUserId(int userId) {
        return reviewDao.getCartByUserId(userId);
    }

    @Override
    public boolean addOrder(Orders orders) {
        return reviewDao.addOrder(orders);
    }

}
