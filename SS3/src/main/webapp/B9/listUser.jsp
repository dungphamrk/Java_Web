<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- listUser.jsp --%>
<%-- Đặt trong thư mục src/main/webapp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="B9.UserManager" %>
<%@ page import="B9.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Danh sách người dùng</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
      body {
        font-family: 'Roboto', Arial, sans-serif;
        line-height: 1.6;
        margin: 20px;
      }
      .container {
        max-width: 800px;
        margin: 0 auto;
      }
      table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
      }
      th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
      }
      th {
        background-color: #f2f2f2;
      }
      .message {
        color: green;
        margin-bottom: 10px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h2>Danh sách người dùng</h2>
      <% String message = (String) request.getAttribute("message"); %>
      <% if (message != null) { %>
      <p class="message"><%= message %></p>
      <% } %>
      <a href="input.jsp">Thêm người dùng mới</a>
      <%
        UserManager userManager = (UserManager) request.getServletContext().getAttribute("userManager");
        List<User> users = (userManager != null) ? userManager.getUsers() : null;
      %>
      <% if (users != null && !users.isEmpty()) { %>
      <table>
        <tr>
          <th>ID</th>
          <th>Tên</th>
          <th>Email</th>
          <th>Hành động</th>
        </tr>
        <% for (User user : users) { %>
        <tr>
          <td><%= user.getId() %></td>
          <td><%= user.getName() %></td>
          <td><%= user.getEmail() %></td>
          <td>
            <button onclick="confirmDelete(<%= user.getId() %>)">Xóa</button>
          </td>
        </tr>
        <% } %>
      </table>
      <% } else { %>
      <p>Chưa có người dùng nào trong danh sách.</p>
      <% } %>
    </div>

    <script>
      function confirmDelete(id) {
        if (confirm("Bạn có chắc muốn xóa người dùng này?")) {
          window.location.href = "../UserServlet?action=delete&id=" + id;
        }
      }
    </script>
  </body>
</html>