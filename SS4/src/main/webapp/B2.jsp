<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/8/2025
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Đăng Nhập</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-color: #f0f0f0;
            }
            .login-container {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                text-align: center;
            }
            input[type="text"], input[type="password"] {
                width: 100%;
                padding: 8px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .message {
                margin-top: 20px;
                color: #d32f2f;
            }
            .success {
                color: #2e7d32;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Đăng Nhập</h2>
            <form method="post" action="login.jsp">
                <label for="username">Tên đăng nhập:</label><br>
                <input type="text" id="username" name="username" required><br>
                <label for="password">Mật khẩu:</label><br>
                <input type="password" id="password" name="password" required><br>
                <input type="submit" value="Đăng Nhập">
            </form>

            <c:if test="${not empty param.username and not empty param.password}">
                <div class="message">
                    <c:if test="${param.username == 'admin' and param.password == '123456'}">
                        <p class="success">Đăng nhập thành công! Chào mừng bạn, admin!</p>
                    </c:if>
                    <c:if test="${param.username != 'admin' or param.password != '123456'}">
                        <p>Thông tin đăng nhập không đúng. Vui lòng nhập lại!</p>
                    </c:if>
                </div>
            </c:if>
        </div>
    </body>
</html>