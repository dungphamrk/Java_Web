package com.data.ss11.controller.B7;

import com.data.ss11.dto.ReviewForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ReviewController {

    @GetMapping("/review")
    public String showForm(Model model) {
        model.addAttribute("reviewForm", new ReviewForm());
        return "/B7/review-form";
    }

    @PostMapping("/review")
    public String submitReview(
            @Valid @ModelAttribute("reviewForm") ReviewForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "/B7/review-form";
        }
        return "/B7/review-success";
    }
}


