package com.data.ss15.controller.B2;

import com.data.ss15.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller

public class StudentController {

    @GetMapping("B2")
    public String getStudents(Model model){
        List<Student> students = Arrays.asList(
                new Student("SV001", "Nguyễn Văn A", 20, "CNTT1", "a@gmail.com", "Hà Nội", "0912345678"),
                new Student("SV002", "Trần Thị B", 21, "CNTT2", "b@gmail.com", "Đà Nẵng", "0912345679"),
                new Student("SV003", "Lê Văn C", 22, "CNTT1", "c@gmail.com", "TP HCM", "0912345680")
        );
        model.addAttribute("students", students);
        return "B2/student-list";
    }
}
