package com.data.ss7.controller;

import com.data.ss7.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final List<Course> courseList = new ArrayList<>();
    private int nextId = 1;

    // Xem danh sách khóa học
    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseList);
        return "listCourse";
    }

    // Hiển thị form thêm khóa học
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }

    // Xử lý khi submit form thêm khóa học
    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        course.setId(nextId++);
        courseList.add(course);
        redirectAttributes.addFlashAttribute("message", "Thêm khóa học thành công!");
        return "redirect:/courses";
    }

    // Xóa khóa học
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable int id, RedirectAttributes redirectAttributes) {
        courseList.removeIf(course -> course.getId() == id);
        redirectAttributes.addFlashAttribute("message", "Xóa khóa học thành công!");
        return "redirect:/courses";
    }
}
