<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.StudentTicketServlet.Student" %>
<html>
    <head>
        <title>Danh sách học sinh đăng ký vé xe</title>
        <style>
            table { border-collapse: collapse; width: 80%; margin: 20px auto; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
        </style>
    </head>
    <body>
        <h2>Danh sách học sinh đã đăng ký vé xe</h2>
        <table>
            <tr>
                <th>Họ và tên</th>
                <th>Lớp</th>
                <th>Loại xe</th>
                <th>Biển số xe</th>
            </tr>
            <%
                List<Student> studentList = (List<Student>) request.getAttribute("studentList");
                if (studentList != null) {
                    for (Student student : studentList) {
            %>
            <tr>
                <td><%= student.getFullName() %></td>
                <td><%= student.getClassName() %></td>
                <td><%= student.getVehicleType() %></td>
                <td><%= student.getLicensePlate() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="4">Không có dữ liệu học sinh.</td></tr>
            <%
                }
            %>
        </table>
    </body>
</html>