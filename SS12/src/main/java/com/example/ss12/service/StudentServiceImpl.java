package com.example.ss12.service;



import com.example.ss12.model.Student;
import com.example.ss12.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public void insert(Student student) {
        studentDAO.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }

    @Override
    public void delete(int id) {
        studentDAO.delete(id);
    }
}

