package com.data.ss8.controller.Bai3;

import com.data.ss8.model.Bai2.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("productControllerBai3")
@RequestMapping("/product")
public class ProductController {
    private static int indexId = 1;

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "Bai3/addProduct";
    }

    @PostMapping("/add")
    public String submitForm(@ModelAttribute("product") Product product, Model model) {
        product.setId(indexId++);
        model.addAttribute("newProduct", product);
        return "Bai3/view";
    }
}