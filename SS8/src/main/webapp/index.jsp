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
        <a href="${pageContext.request.contextPath}/b1">B1</a>
        <a href="products">B2</a>
        <a href="product/add">B3</a>
        <a href="user">B4</a>
        <a href="users">B5</a>
        <a href="employees">B6</a>
        <a href="quiz">B7</a>
        <a href="${pageContext.request.contextPath}/bt8910/farm">bt8910</a>
    </body>
</html>