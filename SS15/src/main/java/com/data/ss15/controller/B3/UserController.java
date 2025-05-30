package com.data.ss15.controller.B3;

import com.data.ss15.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/B3")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "B3/register";
    }

    @PostMapping("/register")
    public String processForm(@Valid @ModelAttribute("user") User user,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "B3/register";
        }

        model.addAttribute("message", "Đăng ký thành công cho người dùng: " + user.getName());
        return "B3/result";
    }
}

