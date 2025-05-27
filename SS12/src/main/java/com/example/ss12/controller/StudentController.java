package com.example.ss12.controller;


import com.example.ss12.model.Student;
import com.example.ss12.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("student") @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student-form";
        }
        studentService.insert(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentService.findById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-form";
    }


    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @ModelAttribute("student") @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student-form";
        }
        student.setId(id);
        studentService.update(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}

