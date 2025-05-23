package com.data.ss11.controller.B1_2_3_4;

import com.data.ss11.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("B1_2_3_4")
public class UserInforController {

    @GetMapping("/userForm")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "B1_2_3_4/userForm";
    }

    @PostMapping("/userForm")
    public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "B1_2_3_4/userForm";
        }
        model.addAttribute("message", "Gửi thông tin người dùng thành công!");
        return "B1_2_3_4/userForm";
    }
}