package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "com.example.StudentTicketServlet", value = "/com.example.StudentTicketServlet")
public class StudentTicketServlet extends HttpServlet {
    // Danh sách tĩnh để lưu thông tin học sinh
    private static List<Student> studentList = new ArrayList<>();

    static {
        // Khởi tạo dữ liệu mẫu
        studentList.add(new Student("Nguyen Van A", "CNTT01", "Xe máy", "51H-12345"));
        studentList.add(new Student("Tran Thi B", "KTPM02", "Xe đạp", "Không có"));
        studentList.add(new Student("Le Van C", "DTVT03", "Khác", "59A-67890"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Chuyển danh sách học sinh sang JSP
        request.setAttribute("studentList", studentList);

        // Chuyển hướng đến trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("listStudent.jsp");
        dispatcher.forward(request, response);
    }

    // Lớp nội bộ để đại diện cho thông tin học sinh
    public static class Student {
        private String fullName;
        private String className;
        private String vehicleType;
        private String licensePlate;

        public Student(String fullName, String className, String vehicleType, String licensePlate) {
            this.fullName = fullName;
            this.className = className;
            this.vehicleType = vehicleType;
            this.licensePlate = licensePlate;
        }

        public String getFullName() { return fullName; }
        public String getClassName() { return className; }
        public String getVehicleType() { return vehicleType; }
        public String getLicensePlate() { return licensePlate; }
    }

    // Phương thức để thêm học sinh (có thể mở rộng để cập nhật danh sách)
    public static void addStudent(Student student) {
        studentList.add(student);
    }
}