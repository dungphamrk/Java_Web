package com.data.ss17.controller;

import com.data.ss17.model.Order;
import com.data.ss17.model.OrderDetail;
import com.data.ss17.model.ProductCart;
import com.data.ss17.service.OrderService;
import com.data.ss17.service.ProductCartService;
import com.data.ss17.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private ProductCartService productCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        var cartItems = productCartService.getCartByCustomerId(customerId);
        var productIds = cartItems.stream().map(ProductCart::getProductId).collect(Collectors.toList());
        var products = productService.getProductsByIds(productIds);
        double total = cartItems.stream()
                .mapToDouble(cart -> {
                    var product = products.stream().filter(p -> p.getId().equals(cart.getProductId())).findFirst().orElse(null);
                    return product != null ? product.getPrice() * cart.getQuantity() : 0;
                })
                .sum();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("products", products);
        model.addAttribute("total", total);
        model.addAttribute("order", new Order());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(
            @RequestParam String recipientName,
            @RequestParam String phoneNumber,
            @RequestParam String address,
            HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        var cartItems = productCartService.getCartByCustomerId(customerId);
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        var productIds = cartItems.stream().map(ProductCart::getProductId).collect(Collectors.toList());
        var products = productService.getProductsByIds(productIds);
        double totalMoney = cartItems.stream()
                .mapToDouble(cart -> {
                    var product = products.stream().filter(p -> p.getId().equals(cart.getProductId())).findFirst().orElse(null);
                    return product != null ? product.getPrice() * cart.getQuantity() : 0;
                })
                .sum();

        var order = new Order();
        order.setCustomerId(customerId);
        order.setRecipientName(recipientName);
        order.setPhoneNumber(phoneNumber);
        order.setAddress(address);
        order.setTotalMoney(totalMoney);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDate.now());

        var orderDetails = cartItems.stream().map(cart -> {
            var detail = new OrderDetail();
            // Note: order.getId() sẽ được tạo tự động bởi Hibernate khi lưu
            detail.setProductId(cart.getProductId());
            detail.setQuantity(cart.getQuantity());
            return detail;
        }).collect(Collectors.toList());

        // Lưu order và orderDetails, gán orderId cho orderDetails
        orderService.saveOrder(order, orderDetails);
        for (OrderDetail detail : orderDetails) {
            detail.setOrderId(order.getId());
            // Cập nhật lại orderDetails với orderId
            try (var session2 = sessionFactory.openSession()) {
                session2.beginTransaction();
                session2.update(detail);
                session2.getTransaction().commit();
            }
        }

        // Clear cart
        for (var cart : cartItems) {
            productCartService.deleteCart(cart.getId());
        }

        return "redirect:/profile";
    }

    @Autowired
    private org.hibernate.SessionFactory sessionFactory;
}