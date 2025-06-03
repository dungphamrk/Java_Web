package com.data.ss17.controller;

import com.data.ss17.service.CustomerService;
import com.data.ss17.service.OrderService;
import com.data.ss17.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    private static final int PAGE_SIZE = 5;

    @GetMapping("/profile")
    public String profile(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        var customer = customerService.getCustomerById(customerId);
        var orders = orderService.getOrdersByCustomerId(customerId, page, PAGE_SIZE);
        long totalOrders = orderService.getTotalOrdersByCustomerId(customerId);
        int totalPages = (int) Math.ceil((double) totalOrders / PAGE_SIZE);

        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @RequestParam String fullName,
            @RequestParam String phoneNumber,
            @RequestParam String address,
            HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        var customer = customerService.getCustomerById(customerId);
        customer.setPhoneNumber(phoneNumber);
        customerService.updateCustomer(customer);
        return "redirect:/profile";
    }

    @PostMapping("/profile/cancel-order")
    public String cancelOrder(@RequestParam Long orderId, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        var orders = orderService.getOrdersByCustomerId(customerId, 0, Integer.MAX_VALUE);
        var order = orders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElse(null);
        if (order != null && "PENDING".equals(order.getStatus())) {
            order.setStatus("CANCELLED");
            orderService.updateOrder(order);
        }
        return "redirect:/profile";
    }
}