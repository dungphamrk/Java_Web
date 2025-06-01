package com.data.ss16.controller;

import com.data.ss16.model.User;
import com.data.ss16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Tên người dùng đã tồn tại");
            return "register";
        }

        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Tên người dùng hoặc email đã tồn tại");
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "Đã có lỗi khi đăng ký: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("error", "Tên người dùng hoặc mật khẩu không đúng");
            return "login";
        }

        if ("ADMIN".equals(existingUser.getRole())) {
            return "redirect:/admin";
        } else {
            return "redirect:/home";
        }
    }
}