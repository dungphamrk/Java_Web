<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <h1><%= "Hello World!" %>
        </h1>
        <br/>
        <a class="nav-link" href="${pageContext.request.contextPath}/admin?section=user">B1 - Quản lý khách hàng</a>
    </body>
</html>