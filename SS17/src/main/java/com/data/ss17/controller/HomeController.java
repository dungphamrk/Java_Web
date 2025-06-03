package com.data.ss17.controller;

import com.data.ss17.model.Product;
import com.data.ss17.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    private static final int PAGE_SIZE = 6;

    @GetMapping("/")
    public String home(Model model, @RequestParam(defaultValue = "0") int page) {
        List<Product> products = productService.getProducts(page, PAGE_SIZE);
        long totalProducts = productService.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "home";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable String id, Model model) {
        Product product = productService.getProductById(Long.valueOf(id));
        model.addAttribute("product", product);
        return "product-detail";
    }
}