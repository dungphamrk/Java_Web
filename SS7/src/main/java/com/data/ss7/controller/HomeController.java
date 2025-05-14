package com.data.ss7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        System.out.println("Home method called");
        return "Welcome to Spring MVC!";
    }

    @RequestMapping("/greet")
    @ResponseBody
    public String greet(@RequestParam String name) {
        return "Hello, " + name + "!";
    }
}