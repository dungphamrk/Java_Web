<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/16/2025
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>B1-result</title>
    </head>
    <body>
        <c:if test="${not empty user}">
            <p>Tên: ${user.name}</p>
            <p>Tuổi: ${user.age}</p>
            <p>Địa chỉ: ${user.address}</p>
        </c:if>
    </body>
</html>
