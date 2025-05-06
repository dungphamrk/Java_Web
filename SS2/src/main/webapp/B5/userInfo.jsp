<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/6/2025
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Information</title>
    </head>
    <body>
        <h2>Thông Tin Đăng Ký</h2>
        <p>Họ và tên: <%= request.getAttribute("name") %></p>
        <p>Email: <%= request.getAttribute("email") %></p>
        <p>Mật khẩu: <%= request.getAttribute("password") %></p>
        <p><a href="UserRegistrationServlet">Quay lại form đăng ký</a></p>
    </body>
</html>
