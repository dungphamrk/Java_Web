package com.data.ss10.controller;

import com.data.ss10.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("B3")
public class AccountController {
    @PostMapping("submit")
    public String register(@ModelAttribute("account") Account account) {
        return "B3/resultForm";
    }

    @GetMapping("register")
    public ModelAndView accountFomr(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("B3/registerForm");
        modelAndView.addObject("newAccount", new Account());
        return modelAndView;
    }
}
