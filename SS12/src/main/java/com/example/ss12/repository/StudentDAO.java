package com.example.ss12.repository;



import com.example.ss12.model.Student;
import com.example.ss12.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAO {

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "{call get_all_students()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setDob(rs.getDate("dob"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student findById(int id) {
        String sql = "{call find_student_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("id"));
                    s.setName(rs.getString("name"));
                    s.setEmail(rs.getString("email"));
                    s.setDob(rs.getDate("dob"));
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Student student) {
        String sql = "{call insert_student(?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, student.getName());
            cs.setString(2, student.getEmail());
            cs.setDate(3, new Date(student.getDob().getTime()));
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        String sql = "{call update_student(?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, student.getId());
            cs.setString(2, student.getName());
            cs.setString(3, student.getEmail());
            cs.setDate(4, new Date(student.getDob().getTime()));
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "{call delete_student(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

