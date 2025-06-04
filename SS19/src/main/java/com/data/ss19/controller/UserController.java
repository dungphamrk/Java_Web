package com.data.ss19.controller;

import com.data.ss19.model.User;
import com.data.ss19.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping(value = "/admin", params = "section=user")
    public String listUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String search,
            Model model) {


        List<User> users = userService.getUsers(page, pageSize, search);
        long totalUsers = userService.getTotalUsers(search);
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("search", search);
        model.addAttribute("section", "user");
        return "user-list";
    }

    @GetMapping("/admin/user/add")
    public String showAddUserForm(Model model, HttpSession session) {

        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/admin/user/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           Model model) {


        if (result.hasErrors()) {
            return "user-form";
        }

        try {
            if (!imageFile.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
                user.setFileImage((String) uploadResult.get("secure_url"));
            }
            if (user.getId() == null) {
                userService.saveUser(user);
            } else {
                userService.updateUser(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi upload ảnh");
            return "user-form";
        }

        return "redirect:/admin?section=user";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {

        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin?section=user";
        }
        model.addAttribute("user", user);
        return "user-form";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
        return "redirect:/admin?section=user";
    }
}