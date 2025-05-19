package com.data.ss10.controller;

import com.data.ss10.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("B1")
public class UserController {
    @GetMapping("/register")
    public ModelAndView userPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("B1/userForm");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("user") User user) {
        return "B1/resultForm";
    }
}
