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
<a href="${pageContext.request.contextPath}/students">bt1</a>
<a href="${pageContext.request.contextPath}/products">bt2</a>
<a href="${pageContext.request.contextPath}/bus">bt3</a>
<a href="${pageContext.request.contextPath}/products-practice">bai tap thuc hanh</a>

</body>
</html>