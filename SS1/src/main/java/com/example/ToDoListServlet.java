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
@WebServlet(name = "com.example.ToDoListServlet", value = "/com.example.ToDoListServlet")
public class ToDoListServlet extends HttpServlet {
    // Danh sách tĩnh để lưu công việc
    private static List<Task> taskList = new ArrayList<>();

    static {
        taskList.add(new Task("Design Website", "Ongoing"));
        taskList.add(new Task("Test Software", "Completed"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Chuyển danh sách công việc sang JSP
        request.setAttribute("taskList", taskList);

        // Chuyển hướng đến trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("todoList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String jobName = request.getParameter("jobName");
        String status = request.getParameter("status");

        if (status != null && !status.isEmpty()) {
            // Cập nhật trạng thái công việc nếu có tham số status
            updateStatus(jobName, status);
        } else if (jobName != null && !jobName.isEmpty()) {
            // Thêm công việc mới nếu chỉ có tham số jobName
            taskList.add(new Task(jobName, "Ongoing"));
        }

        // Chuyển hướng lại để hiển thị danh sách cập nhật
        doGet(request, response);
    }

    // Lớp nội bộ để đại diện cho công việc
    public static class Task {
        private String jobName;
        private String status;

        public Task(String jobName, String status) {
            this.jobName = jobName;
            this.status = status;
        }

        public String getJobName() { return jobName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    // Phương thức để cập nhật trạng thái
    public static void updateStatus(String jobName, String newStatus) {
        for (Task task : taskList) {
            if (task.getJobName().equals(jobName)) {
                task.setStatus(newStatus);
                break;
            }
        }
    }
}