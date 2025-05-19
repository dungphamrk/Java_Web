package com.data.ss10.controller;

import com.data.ss10.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("B2")
public class ProductController {
    @GetMapping("/productForm")
    public ModelAndView formProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("B2/productForm");
        modelAndView.addObject("newProduct", new Product());
        return modelAndView;
    }
    @PostMapping("/submit")
    public String addProduct(@ModelAttribute("product") Product product ) {
        return  "B2/resultForm";
    }
}
