<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/5/2025
  Time: 10:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Kết quả đăng ký</title>
    </head>
    <body>
        <%
            boolean success = Boolean.parseBoolean(String.valueOf(request.getAttribute("success")));
        %>
        <% if (success) { %>
        <h2>🎉 Đăng ký vé xe thành công!</h2>
        <p>Họ tên: <%= request.getAttribute("fullName") %></p>
        <p>Lớp: <%= request.getAttribute("className") %></p>
        <p>Loại xe: <%= request.getAttribute("vehicleType") %></p>
        <p>Biển số xe: <%= request.getAttribute("licensePlate") %></p>
        <% } else { %>
        <h2>❌ Đăng ký thất bại! Vui lòng kiểm tra lại thông tin.</h2>
        <% } %>

    </body>
</html>

