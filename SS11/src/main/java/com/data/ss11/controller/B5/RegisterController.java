package com.data.ss11.controller.B5;

import com.data.ss11.dto.RegisterForm;
import com.data.ss11.validation.AdminGroup;
import com.data.ss11.validation.UserGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class RegisterController {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @GetMapping("/B5/register") // Đổi URL
    public String showForm(Model model) {
        model.addAttribute("form", new RegisterForm());
        return "/B5/register";
    }

    @PostMapping("/B5/register") // Đổi URL
    public String register(@ModelAttribute("form") RegisterForm form, BindingResult result, Model model) {
        Class<?> group = "admin".equals(form.getRole()) ? AdminGroup.class : UserGroup.class;
        Set<ConstraintViolation<RegisterForm>> violations = validator.validate(form, group);

        for (ConstraintViolation<RegisterForm> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            result.rejectValue(propertyPath, "", violation.getMessage());
        }

        if (result.hasErrors()) {
            return "/B5/register";
        }

        model.addAttribute("message", "Đăng ký thành công!");
        return "/B5/register";
    }
}
