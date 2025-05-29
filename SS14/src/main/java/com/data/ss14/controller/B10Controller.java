package com.data.ss14.controller;

import com.data.ss14.model.B10.CategoryForm;
import com.data.ss14.service.B10.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.Locale;

@Controller
@RequestMapping("/category")
public class B10Controller {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showForm(Model model, Locale locale) throws SQLException {
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("categories", categoryService.getCategoriesByLocale(locale));
        return "/B10/category";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute CategoryForm form, Model model, Locale locale) throws SQLException {
        categoryService.addCategory(form);
        return "redirect:/category?lang=" + locale.getLanguage();
    }
}
