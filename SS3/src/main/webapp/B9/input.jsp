<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- input.jsp --%>
<%-- Đặt trong thư mục src/main/webapp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Nhập thông tin người dùng</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
      body {
        font-family: 'Roboto', Arial, sans-serif;
        line-height: 1.6;
        margin: 20px;
      }
      .form-container {
        max-width: 600px;
        margin: 0 auto;
      }
      .form-group {
        margin-bottom: 15px;
      }
      label {
        display: inline-block;
        width: 150px;
      }
      input, button {
        padding: 5px;
      }
    </style>
  </head>
  <body>
    <div class="form-container">
      <h2>Nhập thông tin người dùng</h2>
      <form action="../UserServlet" method="post">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
          <label for="id">ID:</label>
          <input type="number" id="id" name="id" required>
        </div>
        <div class="form-group">
          <label for="name">Tên:</label>
          <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" name="email" required>
        </div>
        <button type="submit">Đăng ký</button>
      </form>
      <br>
      <a href="listUser.jsp">Xem danh sách người dùng</a>
    </div>
  </body>
</html>
