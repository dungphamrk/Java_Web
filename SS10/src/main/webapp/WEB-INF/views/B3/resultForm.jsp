<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/18/2025
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>B1-result</title>
  </head>
  <body>
    <c:if test="${not empty account}">
      <p>Tài khoản: ${account.username}</p>
      <p>Mật khẩu: ${account.password}</p>
      <p>Email: ${account.email}</p>
    </c:if>
  </body>
</html>

