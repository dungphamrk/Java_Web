package com.example.ss12.service;


import com.example.ss12.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int id);
    void insert(Student student);
    void update(Student student);
    void delete(int id);
}
