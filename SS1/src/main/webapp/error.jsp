<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/5/2025
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Lỗi hệ thống</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #fdf2f2;
                color: #b71c1c;
                text-align: center;
                padding-top: 100px;
            }
            .error-box {
                display: inline-block;
                border: 1px solid #e57373;
                background: #ffebee;
                padding: 20px 30px;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <div class="error-box">
            <h2>⚠️ Có lỗi xảy ra!</h2>
            <p>${errorMessage}</p>
        </div>
    </body>
</html>

