package com.data.ss17.controller;

import com.data.ss17.model.Product;
import com.data.ss17.model.ProductCart;
import com.data.ss17.service.ProductCartService;
import com.data.ss17.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @Autowired
    private ProductCartService productCartService;
    @Autowired
    private ProductService productService;

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity,
            HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        var existingCart = productCartService.getCartByCustomerIdAndProductId(customerId, productId);
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            productCartService.addOrUpdateCart(existingCart);
        } else {
            var cart = new ProductCart();
            cart.setCustomerId(customerId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);
            productCartService.addOrUpdateCart(cart);
        }
        return "redirect:/product/" + productId;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        var cartItems = productCartService.getCartByCustomerId(customerId);
        var productIds = cartItems.stream().map(ProductCart::getProductId).collect(Collectors.toList());
        var products = productService.getProductsByIds(productIds);
        double total = cartItems.stream()
                .mapToDouble(cart -> {
                    var product = products.stream().filter(p -> p.getId().equals(cart.getProductId())).findFirst().orElse(null);
                    return product != null ? product.getPrice() * cart.getQuantity() : 0;
                })
                .sum();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("products", products);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(
            @RequestParam Long cartId,
            @RequestParam int quantity,
            HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        // Lấy cart bằng cartId
        var cartItems = productCartService.getCartByCustomerId(customerId);
        var cart = cartItems.stream().filter(c -> c.getId().equals(cartId)).findFirst().orElse(null);
        if (cart != null && quantity > 0) {
            cart.setQuantity(quantity);
            productCartService.addOrUpdateCart(cart);
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String deleteFromCart(
            @RequestParam Long cartId,
            HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        productCartService.deleteCart(cartId);
        return "redirect:/cart";
    }
}