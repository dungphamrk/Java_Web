package B6;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/studentList")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tạo danh sách sinh viên tĩnh
        List<Student> students = new ArrayList<>();
        students.add(new Student("SV001", "Nguyen Van A", 20, 8.5));
        students.add(new Student("SV002", "Tran Thi B", 21, 6.5));
        students.add(new Student("SV003", "Le Van C", 19, 7.8));
        students.add(new Student("SV004", "Pham Thi D", 22, 9.0));
        students.add(new Student("SV005", "Hoang Van E", 20, 5.5));

        // Đưa danh sách vào request
        request.setAttribute("students", students);

        // Chuyển hướng đến JSP
        request.getRequestDispatcher("B6/studentList.jsp").forward(request, response);
    }
}