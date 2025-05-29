package com.data.ss14.controller;

import com.data.ss14.model.B8.Order;
import com.data.ss14.service.B8.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("B8/order")
public class B8Controller {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("order", new Order());
        return "/B8/orderForm";
    }

    @PostMapping
    public String placeOrder(@ModelAttribute Order order, HttpSession session, Model model) {
        orderService.processOrder(order);

        session.setAttribute("currentOrder", order);
        model.addAttribute("message", "Đặt hàng thành công!");
        return "/B8/orderSuccess";
    }

    @GetMapping("/view")
    public String viewOrder(HttpSession session, Model model) {
        Order order = (Order) session.getAttribute("currentOrder");
        if (order == null) {
            model.addAttribute("message", "Không có đơn hàng nào trong phiên làm việc.");
        } else {
            model.addAttribute("order", order);
        }
        return "/B8/orderView";
    }
}

