package com.data.ss11.controller.B8;
import com.data.ss11.model.Category;
import com.data.ss11.service.B8.CategoryService;
import com.data.ss11.service.B8.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CategoryController {
    private final CategoryService categoryService = new CategoryServiceImpl();

    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/B8/listCategory";
    }

    @GetMapping("/addCategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "/B8/addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/B8/addCategory";
        }
        if (categoryService.existsByCategoryName(category.getCategoryName())) {
            result.rejectValue("categoryName", "error.category", "Category name already exists");
            return "/B8/addCategory";
        }
        if (categoryService.addCategory(category)) {
            return "redirect:/categories";
        } else {
            model.addAttribute("error", "Failed to add category");
            return "/B8/addCategory";
        }
    }

    @GetMapping("/editCategory")
    public String showEditCategoryForm(@RequestParam("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            model.addAttribute("error", "Category not found");
            return "/B8/listCategory";
        }
        model.addAttribute("category", category);
        return "/B8/editCategory";
    }

    @PostMapping("/editCategory")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/B8/editCategory";
        }
        if (categoryService.existsByCategoryNameAndNotId(category.getCategoryName(), category.getId())) {
            result.rejectValue("categoryName", "error.category", "Category name already exists");
            return "/B8/editCategory";
        }
        if (categoryService.updateCategory(category)) {
            return "redirect:/categories";
        } else {
            model.addAttribute("error", "Failed to update category");
            return "/B8/editCategory";
        }
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("id") Long id, Model model) {
        if (categoryService.deleteCategory(id)) {
            return "redirect:/categories";
        } else {
            model.addAttribute("error", "Failed to delete category");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/B8/listCategory";
        }
    }
}
