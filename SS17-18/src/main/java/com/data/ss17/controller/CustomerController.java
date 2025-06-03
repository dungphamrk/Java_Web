package com.data.ss17.controller;

import com.data.ss17.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Kiểm tra quyền admin
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("customerRole");
        return "ADMIN".equals(role);
    }

    // Hiển thị danh sách người dùng với phân trang và tìm kiếm
    @GetMapping(value = "/admin", params = "section=customers")
    public String listCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        List<com.data.ss17.model.Customer> customers = customerService.getCustomers(page, pageSize, search);
        long totalCustomers = customerService.getTotalCustomers(search);
        int totalPages = (int) Math.ceil((double) totalCustomers / pageSize);

        model.addAttribute("customers", customers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("search", search);
        model.addAttribute("section", "customers");
        return "admin";
    }

    // Khóa hoặc mở khóa người dùng
    @GetMapping("/customers/toggle/{id}")
    public String toggleCustomerStatus(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        com.data.ss17.model.Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            customerService.toggleCustomerStatus(id);
            model.addAttribute("successMessage", "Cập nhật trạng thái người dùng thành công!");
        } else {
            model.addAttribute("errorMessage", "Người dùng không tồn tại");
        }
        return "redirect:/admin?section=customers";
    }
}