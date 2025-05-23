package com.data.ss11.controller.B6;

import com.data.ss11.dto.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class UserController {

    @GetMapping("/registers")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "/B6/registers";
    }

    @PostMapping("/registers")
    public String submitForm(
            @Valid @ModelAttribute("userForm") UserForm userForm,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "/B6/registers";
        }
        return "/B6/success";
    }
}

