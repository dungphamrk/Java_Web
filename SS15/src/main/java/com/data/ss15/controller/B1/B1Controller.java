package com.data.ss15.controller.B1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class B1Controller {
    @GetMapping("/B1")
    public String helloThymleaf(Model model){
        model.addAttribute("message","Hello Thymleaf");
        return "B1/B1";
    }
}
