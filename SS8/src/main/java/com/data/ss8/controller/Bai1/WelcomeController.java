package com.data.ss8.controller.Bai1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomeController {
    @GetMapping("/b1")
    public String welcome() {
        return "B1/welcome";
    }

}
