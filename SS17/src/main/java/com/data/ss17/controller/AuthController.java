package com.data.ss17.controller;

import com.data.ss17.dto.LoginForm;
import com.data.ss17.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private SessionFactory sessionFactory;

    // Đăng ký (giữ nguyên từ bài trước)
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            customer.setStatus(true);
            customer.setRole("USER");
            session.save(customer);
            session.getTransaction().commit();
            model.addAttribute("successMessage", "Registration successful!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
        }

        return "redirect:/login";
    }

    // Đăng nhập
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login"; // Trả về form nếu có lỗi validation
        }

        try (Session session = sessionFactory.openSession()) {
            Query<Customer> query = session.createQuery("FROM Customer WHERE username = :username AND password = :password", Customer.class);
            query.setParameter("username", loginForm.getUsername());
            query.setParameter("password", loginForm.getPassword());
            Customer customer = query.uniqueResult();

            if (customer == null) {
                model.addAttribute("errorMessage", "Invalid username or password");
                return "login";
            }

            // Kiểm tra role và chuyển hướng
            if ("ADMIN".equals(customer.getRole())) {
                return "redirect:/admin";
            } else if ("USER".equals(customer.getRole())) {
                return "redirect:/home";
            } else {
                model.addAttribute("errorMessage", "Invalid role");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Login failed: " + e.getMessage());
            return "login";
        }
    }

    // Trang admin
    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    // Trang home
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}