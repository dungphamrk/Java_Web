<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/6/2025
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Registration Confirmation</title>
  </head>
  <body>
    <h2>Cảm ơn bạn đã đăng ký!</h2>
    <p>Thông tin của bạn đã được ghi nhận:</p>
    <p>Họ và tên: <%= session.getAttribute("name") %></p>
    <p>Email: <%= session.getAttribute("email") %></p>
    <p><a href="registerByEmail.html">Quay lại form đăng ký</a></p>
  </body>
</html>
