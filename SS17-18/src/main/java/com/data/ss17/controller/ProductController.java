package com.data.ss17.controller;

import com.data.ss17.model.Product;
import com.data.ss17.service.ProductService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private Cloudinary cloudinary;

    // Kiểm tra quyền admin
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("customerRole");
        return "ADMIN".equals(role);
    }

    // Hiển thị danh sách sản phẩm với phân trang và lọc
    @GetMapping(value = "/admin", params = "section=products")
    public String listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minStock,
            Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        List<Product> products = productService.filterProducts(name, minPrice, maxPrice, minStock, page, pageSize);
        long totalProducts = productService.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("name", name);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("minStock", minStock);
        model.addAttribute("section", "products");
        return "admin";
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/products/new")
    public String showAddProductForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("product", new Product());
        return "product-edit";
    }

    // Thêm sản phẩm
    @PostMapping("/products")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult result,
                             @RequestParam("imageFile") MultipartFile imageFile, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            return "product-edit";
        }

        try {
            if (!imageFile.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),
                        Map.of("resource_type", "image"));
                product.setImage(uploadResult.get("secure_url").toString());
            }
            productService.saveProduct(product);
            model.addAttribute("successMessage", "Thêm sản phẩm thành công!");
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Lỗi khi tải ảnh lên: " + e.getMessage());
            return "product-edit";
        }
        return "redirect:/admin?section=products";
    }

    // Hiển thị form sửa sản phẩm
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("errorMessage", "Sản phẩm không tồn tại");
            return "redirect:/admin?section=products";
        }
        model.addAttribute("product", product);
        return "product-edit";
    }

    // Cập nhật sản phẩm
    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute Product product,
                                BindingResult result, @RequestParam("imageFile") MultipartFile imageFile,
                                Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            return "product-edit";
        }

        try {
            if (!imageFile.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),
                        Map.of("resource_type", "image"));
                product.setImage(uploadResult.get("secure_url").toString());
            } else {
                Product existingProduct = productService.getProductById(id);
                product.setImage(existingProduct.getImage());
            }
            product.setId(id);
            productService.saveProduct(product);
            model.addAttribute("successMessage", "Cập nhật sản phẩm thành công!");
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Lỗi khi tải ảnh lên: " + e.getMessage());
            return "product-edit";
        }
        return "redirect:/admin?section=products";
    }

    // Xóa sản phẩm
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            model.addAttribute("successMessage", "Xóa sản phẩm thành công!");
        } else {
            model.addAttribute("errorMessage", "Sản phẩm không tồn tại");
        }
        return "redirect:/admin?section=products";
    }
}