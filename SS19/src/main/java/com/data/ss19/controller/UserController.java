package com.data.ss19.controller;

import com.data.ss19.model.User;
import com.data.ss19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "ADMIN".equals(role);
    }

    // Hiển thị danh sách người dùng với phân trang và tìm kiếm
    @GetMapping(value = "/admin", params = "section=user")
    public String listUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        List<User> user = userService.getUsers(page, pageSize, search);
        long totalUser = userService.getTotalUsers(search);
        int totalPages = (int) Math.ceil((double) totalUser / pageSize);

        model.addAttribute("user",user);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("search", search);
        model.addAttribute("section", "User");
        return "admin";
    }
}