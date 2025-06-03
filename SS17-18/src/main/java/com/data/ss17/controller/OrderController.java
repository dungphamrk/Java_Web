package com.data.ss17.controller;

import com.data.ss17.model.Order;
import com.data.ss17.model.OrderDetail;
import com.data.ss17.model.ProductCart;
import com.data.ss17.service.OrderService;
import com.data.ss17.service.ProductCartService;
import com.data.ss17.service.ProductService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private ProductCartService productCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SessionFactory sessionFactory;

    // Kiểm tra quyền admin
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("customerRole");
        return "ADMIN".equals(role);
    }

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
            detail.setProductId(cart.getProductId());
            detail.setQuantity(cart.getQuantity());
            return detail;
        }).collect(Collectors.toList());

        orderService.saveOrder(order, orderDetails);

        for (var cart : cartItems) {
            productCartService.deleteCart(cart.getId());
        }

        return "redirect:/profile";
    }

    // Hiển thị danh sách đơn hàng với phân trang và lọc
    @GetMapping(value = "/admin", params = "section=orders")
    public String listOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String recipientName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String status,
            Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        LocalDate parsedStartDate = startDate != null && !startDate.isEmpty() ? LocalDate.parse(startDate) : null;
        LocalDate parsedEndDate = endDate != null && !endDate.isEmpty() ? LocalDate.parse(endDate) : null;

        List<Order> orders = orderService.getOrders(page, pageSize, recipientName, parsedStartDate, parsedEndDate, status);
        long totalOrders = orderService.getTotalOrders(recipientName, parsedStartDate, parsedEndDate, status);
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("recipientName", recipientName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("status", status);
        model.addAttribute("statusOptions", List.of("PENDING", "CONFIRMED", "CANCELLED", "SHIPPED", "DELIVERED"));
        model.addAttribute("section", "orders");
        return "admin";
    }

    // Xác nhận đơn hàng
    @GetMapping("/orders/confirm/{id}")
    public String confirmOrder(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Order order = orderService.getOrderById(id);
        if (order != null) {
            orderService.confirmOrder(id);
            model.addAttribute("successMessage", "Xác nhận đơn hàng thành công!");
        } else {
            model.addAttribute("errorMessage", "Đơn hàng không tồn tại");
        }
        return "redirect:/admin?section=orders";
    }

    // Hủy đơn hàng
    @GetMapping("/orders/cancel/{id}")
    public String cancelOrder(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Order order = orderService.getOrderById(id);
        if (order != null) {
            orderService.cancelOrder(id);
            model.addAttribute("successMessage", "Hủy đơn hàng thành công!");
        } else {
            model.addAttribute("errorMessage", "Đơn hàng không tồn tại");
        }
        return "redirect:/admin?section=orders";
    }

    // Chuyển trạng thái đơn hàng
    @PostMapping("/orders/update-status/{id}")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam String status, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Order order = orderService.getOrderById(id);
        if (order != null) {
            orderService.updateOrderStatus(id, status);
            model.addAttribute("successMessage", "Cập nhật trạng thái đơn hàng thành công!");
        } else {
            model.addAttribute("errorMessage", "Đơn hàng không tồn tại");
        }
        return "redirect:/admin?section=orders";
    }
}