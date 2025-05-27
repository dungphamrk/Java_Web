package com.example.ss12.controller;

import com.example.ss12.model.Product;
import com.example.ss12.service.CloudinaryService;
import com.example.ss12.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProductController(ProductService productService, CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("product") @Valid Product product,
                      BindingResult result,
                      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "product-form";
        }
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(imageFile, "products");
                product.setImage(url);
            }
            productService.insert(product);
        } catch (IOException e) {
            result.rejectValue("image", "", "Lỗi upload ảnh!");
            return "product-form";
        }
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @ModelAttribute("product") @Valid Product product,
                       BindingResult result,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "product-form";
        }
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(imageFile, "products");
                product.setImage(url);
            } else {
                Product old = productService.findById(id);
                product.setImage(old.getImage());
            }
            product.setId(id);
            productService.update(product);
        } catch (IOException e) {
            result.rejectValue("image", "", "Lỗi upload ảnh!");
            return "product-form";
        }
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
