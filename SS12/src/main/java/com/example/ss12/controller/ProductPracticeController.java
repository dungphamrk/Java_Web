package com.example.ss12.controller;

import com.example.ss12.model.ProductPractice;
import com.example.ss12.service.CloudinaryService;
import com.example.ss12.service.ProductPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products-practice")
public class ProductPracticeController {

    private final ProductPracticeService productPracticeService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProductPracticeController(ProductPracticeService productPracticeService, CloudinaryService cloudinaryService) {
        this.productPracticeService = productPracticeService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String list(Model model) {
        List<ProductPractice> products = productPracticeService.findAll();
        model.addAttribute("products", products);
        return "product-practice-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductPractice());
        return "product-practice-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("product") @Valid ProductPractice product,
                      BindingResult result,
                      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "product-practice-form";
        }
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(imageFile, "products");
                product.setImage(url);
            }
            productPracticeService.insert(product);
        } catch (IOException e) {
            result.rejectValue("image", "", "Lỗi upload ảnh!");
            return "product-practice-form";
        }
        return "redirect:/products-practice";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        ProductPractice product = productPracticeService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product-practice-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id,
                       @ModelAttribute("product") @Valid ProductPractice product,
                       BindingResult result,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "product-practice-form";
        }
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String url = cloudinaryService.uploadFile(imageFile, "products");
                product.setImage(url);
            } else {
                ProductPractice old = productPracticeService.findById(id);
                if (old != null) {
                    product.setImage(old.getImage());
                }
            }
            product.setId(id);
            productPracticeService.update(product);
        } catch (IOException e) {
            result.rejectValue("image", "", "Lỗi upload ảnh!");
            return "product-practice-form";
        }
        return "redirect:/products-practice";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productPracticeService.delete(id);
        return "redirect:/products-practice";
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<ProductPractice> products = productPracticeService.searchByName(name);
        model.addAttribute("products", products);
        return "product-practice-list";
    }
}
