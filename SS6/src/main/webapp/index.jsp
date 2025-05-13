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
        <a href="product">B3</a>
        <a href="views/B5/B5.jsp">B5</a>
        <a href="/employee">B4</a>
        <a href="${pageContext.request.contextPath}/books">B1+2</a>
    </body>
</html>