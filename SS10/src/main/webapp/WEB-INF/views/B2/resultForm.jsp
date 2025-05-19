<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/16/2025
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>B2-result</title>
  </head>
  <body>
    <c:if test="${not empty product}">
      <p>Tên: ${product.name}</p>
      <p>Mô tả: ${product.description}</p>
      <p>Giá: ${product.price}</p>
    </c:if>
  </body>
</html>
